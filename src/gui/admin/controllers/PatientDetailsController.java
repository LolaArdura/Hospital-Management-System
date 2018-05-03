package gui.admin.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCRoomController;
import model.Patient;
import model.Room;
import model.User;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.PatientInterface;
import interfaces.RoomInterface;

public class PatientDetailsController implements Initializable {
	public enum paneType {
		ADMIN, NEW_PATIENT, RECEPTIONIST, DOCTOR, NURSE
	}

	private paneType permission;

	@FXML
	private Pane mainPane;

	private Patient patient;

	@FXML
	private GridPane detailsPane;

	@FXML
	private Label nameLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label birthLabel;

	@FXML
	private TextField dayTextField;

	@FXML
	private TextField monthTextField;

	@FXML
	private TextField yearTextField;

	@FXML
	private Label sexLabel;

	@FXML
	private TextField sexTextField;

	@FXML
	private Label admissionLabel;

	@FXML
	private DatePicker admissionDate;

	@FXML
	private Label roomLabel;

	@FXML
	private ComboBox<Room> roomBox;

	@FXML
	private GridPane medicalInfoPane;

	@FXML
	private Label treatmentLabel;

	@FXML
	private Button treatmentButton;

	@FXML
	private Label medicalConditionLabel;

	@FXML
	private TextArea medicalConditionArea;

	@FXML
	private Label billsLabel;

	@FXML
	private Button billsButton;

	@FXML
	private ToolBar buttonsToolBar;

	@FXML
	private RadioButton femaleButton;

	@FXML
	private RadioButton maleButton;

	@FXML
	private Button okButton;

	@FXML
	private Button dischargeButton;

	@FXML
	public void okButtonClicked(ActionEvent event) {
		PatientInterface controller = JDBCPatientController.getPatientController();
		Patient p = getPatient();
		if (p != null) {
			if (permission.equals(paneType.ADMIN) || permission.equals(paneType.RECEPTIONIST)) {
				try {
					this.patient.setName(p.getName());
					this.patient.setDob(p.getDob());
					this.patient.setGender(p.getGender());
					this.patient.setDiagnose(p.getDiagnose());
					this.patient.setRoom(p.getRoom());
					this.patient.setDateAdmission(p.getDateAdmission());
					controller.updatePatient(this.patient);
					changePane();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				if (permission.equals(paneType.NEW_PATIENT)) {
					try {
						controller.insertCompletePatient(p);
						Alert a = new Alert(AlertType.INFORMATION);
						a.setHeaderText("Admission completed");
						a.setContentText("The new patient has been succesfully registered");
						changePane();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		}

	}

	public void initComponents(Patient patient, Pane mainPane, paneType permission) {

		this.permission = permission;
		this.patient = patient;
		if (permission.equals(paneType.NEW_PATIENT)) {
			treatmentLabel.setVisible(false);
			treatmentButton.setVisible(false);
			billsLabel.setVisible(false);
			billsButton.setVisible(false);
			setFreeRooms();
		} else {
			nameTextField.setText(patient.getName());
			dayTextField.setText("" + patient.getDob().toLocalDate().getDayOfMonth());
			monthTextField.setText("" + patient.getDob().toLocalDate().getMonthValue());
			yearTextField.setText("" + patient.getDob().toLocalDate().getYear());
			sexTextField.setText(patient.getGender().name().toLowerCase());
			admissionDate.setValue(patient.getDateAdmission().toLocalDate());
			medicalConditionArea.setText(patient.getDiagnose());
			roomBox.getItems().add(patient.getRoom());
			roomBox.getSelectionModel().select(patient.getRoom());
			this.mainPane = mainPane;

			if (permission.equals(paneType.DOCTOR) || permission.equals(paneType.NURSE)) {
				admissionDate.setEditable(false);
				nameTextField.setEditable(false);
				yearTextField.setEditable(false);
				sexTextField.setEditable(false);
				admissionDate.setEditable(false);
				medicalConditionArea.setEditable(false);
				billsLabel.setVisible(false);
				billsButton.setVisible(false);
			}
			if (permission.equals(paneType.DOCTOR)) {
				medicalConditionArea.setEditable(true);
			}

			if (permission.equals(paneType.ADMIN)) {
				medicalConditionArea.setEditable(false);
			}

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		roomBox.setCellFactory(new Callback<ListView<Room>, ListCell<Room>>() {

			@Override
			public ListCell<Room> call(ListView<Room> r) {
				ListCell<Room> cell = new ListCell<Room>() {

					@Override
					protected void updateItem(Room item, boolean b) {
						super.updateItem(item, b);
						if (item != null) {
							setText("" + item.getNumber());
						}
					}

				};
				return cell;
			}
		});

		nameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (nameTextField.getText().trim().equals("")) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("A name needs to be specified");
						a.showAndWait();
						nameTextField.requestFocus();
					}
				}
			}

		});

		dayTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (!dayTextField.getText().trim().equals("")) {
						try {
							Integer.parseInt(dayTextField.getText());

						} catch (NumberFormatException ex) {
							Alert a = new Alert(AlertType.ERROR);
							a.setTitle("ERROR");
							a.setContentText("This field needs to be a number");
							a.showAndWait();
							dayTextField.requestFocus();
						}
					}
				}
			}
		});

		monthTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (!monthTextField.getText().trim().equals("")) {
						try {
							Integer.parseInt(monthTextField.getText());

						} catch (NumberFormatException ex) {
							Alert a = new Alert(AlertType.ERROR);
							a.setTitle("ERROR");
							a.setContentText("This field needs to be a number");
							a.showAndWait();
							monthTextField.requestFocus();
						}
					}
				}
			}
		});

		yearTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (!yearTextField.getText().trim().equals("")) {
						try {
							Integer.parseInt(yearTextField.getText());

						} catch (NumberFormatException ex) {
							Alert a = new Alert(AlertType.ERROR);
							a.setTitle("ERROR");
							a.setContentText("This field needs to be a number");
							a.showAndWait();
							yearTextField.requestFocus();
						}
					}
				}
			}
		});

	}

	private void setFreeRooms() {
		try {
			RoomInterface controller = JDBCRoomController.getRoomController();
			List<Room> freeRooms = controller.getFreeRooms();
			roomBox.getItems().clear();
			roomBox.getItems().addAll(freeRooms);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void changePane() {

		if (permission.equals(paneType.ADMIN)) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
				GridPane patientsViewPane = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(patientsViewPane);
				patientsViewPane.prefHeightProperty().bind(mainPane.heightProperty());
				patientsViewPane.prefWidthProperty().bind(mainPane.widthProperty());

				PatientsViewPaneController controller = loader.<PatientsViewPaneController>getController();
				controller.initComponents(mainPane);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else {
			if (permission.equals(paneType.NEW_PATIENT)) {
				mainPane.getChildren().clear();
			}

			else {

			}
		}
	}

	private Patient getPatient() {
		String name = nameTextField.getText();
		Integer day = Integer.parseInt(dayTextField.getText());
		Integer month = Integer.parseInt(monthTextField.getText());
		Integer year = Integer.parseInt(yearTextField.getText());
		Date dob = Date.valueOf(
				LocalDate.parse("" + day + "-" + month + "-" + year, DateTimeFormatter.ofPattern("dd-mm-yyyy")));
		Patient.sex sex;
		if (!maleButton.isSelected() && !femaleButton.isSelected()) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("No sex defined for the patient");
			a.showAndWait();
		} else {
			if (femaleButton.isSelected()) {
				sex = Patient.sex.FEMALE;
			} else {
				sex = Patient.sex.MALE;
			}
			Date admission = Date.valueOf(admissionDate.getValue());
			if (admission == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("No admission date defined for the patient");
				a.showAndWait();
			} else {
				Room room = roomBox.getSelectionModel().getSelectedItem();
				if (room == null) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("No room defined for the patient");
					a.showAndWait();
				} else {
					String medicalCondition = medicalConditionArea.getText();
					Patient p = new Patient(name, sex, medicalCondition, dob, admission, room);
					return p;
				}
			}

		}
		Patient p = null;
		return p;

	}
}
