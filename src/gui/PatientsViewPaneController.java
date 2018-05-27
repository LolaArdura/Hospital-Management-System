package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import gui.PatientDetailsController.paneType;
import interfaces.NurseInterface;
import interfaces.PatientInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdbcManager.JDBCNurseController;
import jdbcManager.JDBCPatientController;
import model.Doctor;
import model.Nurse;
import model.Patient;
import model.User;

public class PatientsViewPaneController implements Initializable {
	
	private String permission;
	
	private Doctor doctor;
	
	private Nurse nurse;

	private Pane mainPane;

	@FXML
	private GridPane patientViewPane;

	@FXML
	private TableView<Patient> patientsTable;

	@FXML
	private TableColumn<Patient, Integer> idColumn;

	@FXML
	private TableColumn<Patient, String> nameColumn;
	
    @FXML
	private Label searchLabel;

	@FXML
	private JFXTextField floatTextField;

	@FXML
	private Button viewDetailsButton;

	public void viewDetailsClicked(ActionEvent event) {

		try {
			Patient patient = (Patient) patientsTable.getSelectionModel().getSelectedItem();
			if (patient != null) {
				if(permission.equals("nurse")) {
					patient=JDBCPatientController.getPatientController().searchPatientById(patient.getId());
				}
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
				GridPane detailsPane = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(detailsPane);
				detailsPane.prefHeightProperty().bind(mainPane.heightProperty());
				detailsPane.prefWidthProperty().bind(mainPane.widthProperty());

				PatientDetailsController controller = loader.<PatientDetailsController>getController();
				controller.initComponents(patient, mainPane, PatientDetailsController.paneType.valueOf(permission.toUpperCase()),doctor,nurse);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No selected");
				alert.setContentText("A patient needs to be selected in order to view details");
				alert.setHeaderText("No patient selected");
				alert.showAndWait();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	
	@FXML
	public void textFieldKeyTyped(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			String name=floatTextField.getText();
			PatientInterface controller = JDBCPatientController.getPatientController();
			try {
				List<Patient> patients=controller.searchPatientByName(name);
				setPatients(patients);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	 }
	   
	   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("Id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("Name"));
		
	}

	private ObservableList<Patient> setPatients() {
		ObservableList<Patient> patients = FXCollections.observableArrayList();
		if(permission.equals("nurse")) {
			try {
			NurseInterface nurseController=JDBCNurseController.getNurseController();
			patients.addAll(nurseController.getPatientsFromNurse(nurse));
			return patients;
			}catch(Exception ex) {
				ex.printStackTrace();
				patients.clear();
				return patients;
			}
		}
		else {
		
		try {
			PatientInterface controller = JDBCPatientController.getPatientController();
			patients.addAll(controller.getPatientWithoutTreatmentsAndBills());
			return patients;
		} catch (Exception ex) {
			ex.printStackTrace();
			patients.clear();
			return patients;
		}
		
	  }
	}

	public void initComponents(Pane mainPane, String permission, Doctor doctor, Nurse nurse) {
		this.mainPane = mainPane;
		this.permission=permission;
		this.doctor=doctor;
		this.nurse=nurse;
		patientsTable.setItems(setPatients());
	}
	
	private void setPatients(List<Patient> patients) {
		ObservableList<Patient> observablePatients=FXCollections.observableArrayList();
		observablePatients.addAll(patients);
		patientsTable.getItems().clear();
		patientsTable.getItems().setAll(observablePatients);
	}

}
