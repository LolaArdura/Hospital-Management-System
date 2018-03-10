package gui;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Patient;

public class PatientsViewPaneController implements Initializable {
	
	   @FXML
	   private AnchorPane patientViewPane;
	
	    @FXML
	    private TableView<Patient> patientsTable;

	    @FXML
	    private TableColumn<Patient, Integer> idColumn;

	    @FXML
	    private TableColumn<Patient, String> nameColumn;

	    @FXML
	    private Button viewDetailsButton;
	    
	    public void viewDetailsClicked (ActionEvent event) {
	    	try {
	    	Patient patient= patientsTable.getSelectionModel().getSelectedItem();
	    	System.out.println(patient);
	    	FXMLLoader loader= new FXMLLoader (getClass().getResource("testPane.fxml"));
	    	AnchorPane adminPane = (AnchorPane)loader.load();
	    	patientViewPane.getChildren().clear();
	    	patientViewPane.getChildren().add(adminPane);
	    	TestPaneController controller=loader.<TestPaneController>getController();
	    	controller.initComponents(patient);
	    
	    	}catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			idColumn.setCellValueFactory(new PropertyValueFactory <Patient,Integer>("Id"));
			nameColumn.setCellValueFactory(new PropertyValueFactory <Patient,String>("Name"));
			patientsTable.setItems(setPatients());
		}
	    
		private ObservableList<Patient> setPatients(){
			ObservableList<Patient> patients = FXCollections.observableArrayList();
			patients.add(new Patient(123,"aa",Patient.sex.MEN));
			
			//patients.addAll(PatientController.getPatientController().getAllPatients();
			return patients;
		}
	    
	    

	}

