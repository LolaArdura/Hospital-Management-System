package gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCRoomController;
import jdbcManager.JDBCTreatmentController;
import jpaManager.JPAPatientController;
import model.Bills;
import model.Doctor;
import model.Nurse;
import model.Patient;
import model.Room;
import model.Sex;
import model.Treatment;
import xmlManager.XmlPatient;

import java.io.File;
import java.net.URL;
import java.sql.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.PatientInterface;
import interfaces.RoomInterface;

public class PatientDetailsController implements Initializable {
	public enum paneType {
		ADMIN, NEW_PATIENT, RECEPTIONIST, DOCTOR, NURSE, NEW_PATIENT_XML
	}

	private paneType permission;
	
	private Doctor doctor;
	private Nurse nurse;

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
	private Button generateXMLButton;
	
	@FXML
	public void dischargeButtonClicked(ActionEvent e) {
		Alert a= new Alert(AlertType.CONFIRMATION,"Do you want to generate a billing report?",
				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
		a.setHeaderText("Billing report");
		String confirmation = a.showAndWait().get().getText();
		if (confirmation.equals("Yes")) {
			try {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("BillsReportPane.fxml"));
				GridPane billsPane=(GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(billsPane);
			    billsPane.prefHeightProperty().bind(mainPane.heightProperty());
			    billsPane.prefWidthProperty().bind(mainPane.widthProperty());
			    
			    BillsReportController controller=loader.<BillsReportController>getController();
			    controller.initComponents(patient, mainPane,permission.name().toLowerCase(), true);
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			Alert alert= new Alert(AlertType.CONFIRMATION,"Do you want to discharge the patient?",
					new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
			a.setHeaderText("Discharge patient");
			confirmation = alert.showAndWait().get().getText();
			if (confirmation.equals("Yes")) {
				try {
				PatientInterface controller = JDBCPatientController.getPatientController();
				controller.deletePatient(patient);
				changePane();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void billsButtonClicked(ActionEvent e) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("BillsReportPane.fxml"));
			GridPane billsPane=(GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(billsPane);
		    billsPane.prefHeightProperty().bind(mainPane.heightProperty());
		    billsPane.prefWidthProperty().bind(mainPane.widthProperty());
		    
		    BillsReportController controller=loader.<BillsReportController>getController();
		    controller.initComponents(patient, mainPane, permission.name().toLowerCase(), false);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	@FXML
	public void treatmentsButtonClicked(ActionEvent e) {
		try {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("TreatmentsPane.fxml"));
		GridPane treatmentsPane=(GridPane) loader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(treatmentsPane);
	    treatmentsPane.prefHeightProperty().bind(mainPane.heightProperty());
	    treatmentsPane.prefWidthProperty().bind(mainPane.widthProperty());
	    
	    TreatmentsController controller=loader.<TreatmentsController>getController();
	    if(permission.equals(PatientDetailsController.paneType.DOCTOR)) {
	    	controller.initComponents(patient,mainPane,doctor,permission);
	    }
	    else {
	    controller.initComponents(patient, mainPane,null, permission);
	    }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void generateXMLClicked(ActionEvent event) {
		try {
		FileChooser fileChooser= new FileChooser();
		fileChooser.setTitle("Save Patient");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files","*.xml"));
		File patientFile= fileChooser.showSaveDialog(detailsPane.getScene().getWindow());
		
		if(patientFile!=null) {
			List<Treatment> treatments= JDBCPatientController.getPatientController().getTreatmentsFromPatient(patient);
			if(treatments!=null) {
			patient.setListOfTreatments(treatments);
			}
			List<Bills> bills= JDBCPatientController.getPatientController().getBillsFromPatient(patient);
			if(bills!=null) {
			patient.setListOfBills(bills);
			}
			XmlPatient.marshal(patient, patientFile);
		
			
			Alert alert= new Alert(AlertType.CONFIRMATION,
					patientFile.getName().substring(0, patientFile.getName().length()-4)+".html",
				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
			alert.setTitle("HTML");
			alert.setHeaderText("Do you want to generate an HTML page?");
			String confirmation = alert.showAndWait().get().getText();
			if (confirmation.equals("Yes")) {
				FileChooser fileChooser2= new FileChooser();
				fileChooser2.setTitle("Save Patient");
				fileChooser2.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML Files","*.html"));
				File htmlFile= fileChooser2.showSaveDialog(detailsPane.getScene().getWindow());
				if(htmlFile!=null) {
				XmlPatient.xml2Html(patientFile, htmlFile);
				}
			}
		}
		
		}catch(Exception ex) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText("Error saving");
			alert.setContentText("The patient could not be saved");
			alert.showAndWait();
			ex.printStackTrace();
		}
	}
	

	@FXML
	public void okButtonClicked(ActionEvent event) {
		PatientInterface controller = JPAPatientController.getPatientController();
		Patient p = getPatient();
		if (p != null) {
			if (permission.equals(paneType.ADMIN) || permission.equals(paneType.RECEPTIONIST) || permission.equals(paneType.NEW_PATIENT_XML)) {
				try {
					this.patient.setName(p.getName());
					this.patient.setDob(p.getDob());
					this.patient.setGender(p.getGender());
					this.patient.setDiagnose(p.getDiagnose());
					this.patient.setDateAdmission(p.getDateAdmission());
					if(permission.equals(paneType.NEW_PATIENT_XML)) {
						controller.addRoomToPatient(patient,roomBox.getSelectionModel().getSelectedItem());
					}
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
				else {
					if(permission.equals(paneType.DOCTOR)) {
						try {
						this.patient.setDiagnose(p.getDiagnose()); //Doctors can just change the diagnose of the patient
						controller.updatePatient(patient);
						changePane();
						}catch(Exception ex) {
							ex.printStackTrace();
						}
					}
					else {
						changePane();
					}
				}
			}

		}

	}

	public void initComponents(Patient patient, Pane mainPane, paneType permission, Doctor doctor, Nurse nurse) {
		this.doctor=doctor;
		this.nurse=nurse;
        this.mainPane=mainPane;
		this.permission = permission;
		this.patient = patient;
		dischargeButton.setVisible(true);
		generateXMLButton.setVisible(true);
		
		if (permission.equals(paneType.NEW_PATIENT)) {
			dischargeButton.setVisible(false);
			treatmentLabel.setVisible(false);
			treatmentButton.setVisible(false);
			billsLabel.setVisible(false);
			billsButton.setVisible(false);
			generateXMLButton.setVisible(false);
			setFreeRooms();
		} else {
			try {
			nameTextField.setText(patient.getName());
			if(patient.getDob().toLocalDate().getDayOfMonth()<10) {
				dayTextField.setText("0" + patient.getDob().toLocalDate().getDayOfMonth());
			}
			else {
			dayTextField.setText("" + patient.getDob().toLocalDate().getDayOfMonth());
			}
			monthTextField.setText("0" + patient.getDob().toLocalDate().getMonthValue());
			yearTextField.setText("" + patient.getDob().toLocalDate().getYear());
			if(patient.getGender().name().toLowerCase().equals("male")) {
				maleButton.setSelected(true);
			}
			else {
				femaleButton.setSelected(true);
			}
			admissionDate.setValue(patient.getDateAdmission().toLocalDate());
			medicalConditionArea.setText(patient.getDiagnose());
			roomBox.getItems().add(patient.getRoom());
			roomBox.getSelectionModel().select(patient.getRoom());
			this.mainPane = mainPane;
			
			if(permission.equals(paneType.NEW_PATIENT_XML)) {
				generateXMLButton.setVisible(false);
				dischargeButton.setVisible(false);
				treatmentLabel.setVisible(false);
				treatmentButton.setVisible(false);
				billsLabel.setVisible(false);
				billsButton.setVisible(false);
				setFreeRooms();
				roomBox.getItems().add(patient.getRoom());
				roomBox.getSelectionModel().select(patient.getRoom());
			}

			if (permission.equals(paneType.DOCTOR) || permission.equals(paneType.NURSE)) {
				generateXMLButton.setVisible(false);
				dischargeButton.setVisible(false);
				admissionDate.setEditable(false);
				nameTextField.setEditable(false);
				yearTextField.setEditable(false);
				buttonsToolBar.setDisable(true);
				admissionDate.setEditable(false);
				medicalConditionArea.setEditable(false);
				billsLabel.setVisible(false);
				billsButton.setVisible(false);
				
			}
			if (permission.equals(paneType.DOCTOR)) {
		
				medicalConditionArea.setEditable(true);
				
			}

			if (permission.equals(paneType.ADMIN)) {
				generateXMLButton.setVisible(true);
				medicalConditionArea.setEditable(false);
				dischargeButton.setVisible(false);
				
			}
			}catch(Exception ex) {
				ex.printStackTrace();
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

		

	}

	private void setFreeRooms() {

		try {
			RoomInterface controller = JDBCRoomController.getRoomController();
			List<Room> freeRooms=controller.getFreeRooms();
			roomBox.getItems().clear();
			roomBox.getItems().addAll(freeRooms);
		} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	

	
	private void changePane() {
		if (permission.equals(paneType.NEW_PATIENT)|permission.equals(paneType.NEW_PATIENT_XML)) {
			mainPane.getChildren().clear();	
		}
		
		else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
				GridPane patientsViewPane = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(patientsViewPane);
				patientsViewPane.prefHeightProperty().bind(mainPane.heightProperty());
				patientsViewPane.prefWidthProperty().bind(mainPane.widthProperty());

				PatientsViewPaneController controller = loader.<PatientsViewPaneController>getController();
				controller.initComponents(mainPane,permission.name().toLowerCase(),doctor,nurse);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			}
	}
	

	private Patient getPatient() {
		try {
		String name = nameTextField.getText();
		if (nameTextField.getText().trim().equals("")) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("ERROR");
			a.setContentText("A name needs to be specified");
			a.showAndWait();
			nameTextField.requestFocus();
		}
		else {
		Date dob = Date.valueOf(
				LocalDate.parse(dayTextField.getText()+"-" + monthTextField.getText() + "-" + yearTextField.getText(), 
						DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		Sex sex;
		if (!maleButton.isSelected() && !femaleButton.isSelected()) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("No sex defined for the patient");
			a.showAndWait();
		} else {
			if (femaleButton.isSelected()) {
				sex = Sex.female;
			} else {
				sex = Sex.male;
			}
			LocalDate admissionDate = this.admissionDate.getValue();
			if (admissionDate == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("No admission date defined for the patient");
				a.showAndWait();
			} else {
				Date admission=Date.valueOf(admissionDate);
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
		}
		Patient p = null;
	    return p;
		}catch(NumberFormatException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("ERROR");
				a.setContentText("Dat, year and month should be expressed as numbers");
				a.showAndWait();
				Patient p = null;
			    return p;
			}catch( DateTimeParseException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("ERROR");
				a.setContentText("Day, year and month should be expressed as numbers following:\n"
						+ "(dd/mm/yyyy");
				a.showAndWait();
				Patient p = null;
			    return p;
			}
		}

	}

