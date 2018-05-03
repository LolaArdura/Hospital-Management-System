package gui.admin.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import interfaces.NurseInterface;
import interfaces.PatientInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import jdbcManager.JDBCNurseController;
import jdbcManager.JDBCPatientController;
import model.Nurse;
import model.Patient;
import model.Patient.sex;

public class NursePatientController implements Initializable {

	private Pane mainPane;

	private Nurse nurse;

	@FXML
	private GridPane patientNursePane;

	@FXML
	private ListView<Patient> patientsList;

	@FXML
	private ListView<Patient> nurseList;

	@FXML
	private Label nurseLabel;

	@FXML
	private Label patientsLabel;

	@FXML
	private Button assignPatientButton;

	@FXML
	private Button removePatientButton;

	@FXML
	private Button okButton;

	public void initComponents(Pane mainPane, Nurse nurse) {
		try {
			this.mainPane = mainPane;
			this.nurse = nurse;
			setAllPatients();
			setPatientsFromNurse();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	void assignPatientClciked(ActionEvent event) {
		Patient patient = patientsList.getSelectionModel().getSelectedItem();
		if (patient != null) {
			try {
				NurseInterface controller = JDBCNurseController.getNurseController();
				controller.addPatientToNurse(nurse, patient);
				patientsList.getItems().remove(patient);
				nurseList.getItems().add(patient);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("ERROR");
			a.setHeaderText("No selection");
			a.setContentText("A patient needs to be selected");
			a.showAndWait();
		}

	}

	@FXML
	void removePatientClicked(ActionEvent event) {
		Patient patient = nurseList.getSelectionModel().getSelectedItem();
		if (patient != null) {
			try {
				NurseInterface controller = JDBCNurseController.getNurseController();
				controller.deletePatientFromNurse(nurse, patient);
				patientsList.getItems().add(patient);
				nurseList.getItems().remove(patient);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("ERROR");
			a.setHeaderText("No selection");
			a.setContentText("A patient needs to be selected");
			a.showAndWait();
		}
	}

	@FXML
	void okButtonClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NursesPane.fxml"));
			GridPane nursesPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(nursesPane);
			nursesPane.prefHeightProperty().bind(mainPane.heightProperty());
			nursesPane.prefWidthProperty().bind(mainPane.widthProperty());

			NursesViewPaneController nursesController = loader.<NursesViewPaneController>getController();
			nursesController.initComponents(mainPane, false);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		patientsList.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {

			@Override
			public ListCell<Patient> call(ListView<Patient> patient) {
				ListCell<Patient> cell = new ListCell<Patient>() {

					@Override
					protected void updateItem(Patient item, boolean bln) {
						super.updateItem(item, bln);
						if (item != null) {
							setText(item.getId() + ": " + item.getName());
						}
					}
				};
				return cell;
			}

		});

		nurseList.setCellFactory(new Callback<ListView<Patient>, ListCell<Patient>>() {

			@Override
			public ListCell<Patient> call(ListView<Patient> patient) {
				ListCell<Patient> cell = new ListCell<Patient>() {

					@Override
					protected void updateItem(Patient item, boolean bln) {
						super.updateItem(item, bln);
						if (item != null) {
							setText(item.getId() + ": " + item.getName());
						}
					}
				};
				return cell;
			}

		});

	}

	private void setPatientsFromNurse() {
		try {
			ObservableList<Patient> patients = FXCollections.observableArrayList();
			NurseInterface controller = JDBCNurseController.getNurseController();
			patients.addAll(controller.getPatientsFromNurse(nurse));
			nurseList.getItems().clear();
			nurseList.setItems(patients);
			patientsList.getItems().removeAll(patients);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setAllPatients() {
		try {
			ObservableList<Patient> patients = FXCollections.observableArrayList();
			PatientInterface controller = JDBCPatientController.getPatientController();
			patients.addAll(controller.getAllPatients());
			patientsList.getItems().clear();
			patientsList.setItems(patients);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
