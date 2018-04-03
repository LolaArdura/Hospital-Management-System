package gui;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Patient;


public class PatientsViewPaneController implements Initializable{
	
	private Pane adminMainPane;

    @FXML
    private GridPane patientViewPane;

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
	    	Patient patient= (Patient) patientsTable.getSelectionModel().getSelectedItem();
	    	FXMLLoader loader= new FXMLLoader (getClass().getResource("PatientDetails.fxml"));
	     	GridPane detailsPane = (GridPane)loader.load();
	     	adminMainPane.getChildren().clear();
	     	adminMainPane.getChildren().add(detailsPane);
	    	detailsPane.prefHeightProperty().bind(adminMainPane.heightProperty());
	    	detailsPane.prefWidthProperty().bind(adminMainPane.widthProperty());
	     	
	    	PatientDetailsController controller=loader.<PatientDetailsController>getController();
	    	controller.initComponents(patient,adminMainPane);
	    
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
			patients.add(new Patient(123,"aa",Patient.sex.MEN," ",new Date(2011,12,23), new Date (2018,3,1)));
			
			//patients.addAll(PatientController.getPatientController().getAllPatients();
			return patients;
		}
	
	    
	    public void initComponents(Pane adminPane) {
	    	adminMainPane=adminPane;
	    }

	}

