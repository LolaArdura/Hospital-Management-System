package gui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Patient;
import model.User;
import xmlManager.XmlPatient;

public class ReceptionistPaneController {
	private User user;

    @FXML
    private BorderPane receptionistPane;

    @FXML
    private MenuItem myProfileItem;

    @FXML
    private MenuItem signOutItem;

    @FXML
    private VBox buttonsVBox;

    @FXML
    private Label menuLabel;

    @FXML
    private Accordion accordionContainer;

    @FXML
    private TitledPane patientsTitledPane;

    @FXML
    private VBox patientsVBox;

    @FXML
    private Button viewAllButton;

    @FXML
    private Button registrationButton;
    
    @FXML
    private Button loadPatientButton;

    @FXML
    private Pane mainPane;

    @FXML
	void registrationButtonClicked(ActionEvent event) {
        try {
        	 FXMLLoader loader=new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
             GridPane patientsView= (GridPane) loader.load();
             mainPane.getChildren().clear();
             mainPane.getChildren().add(patientsView);
             patientsView.prefHeightProperty().bind(mainPane.heightProperty());
             patientsView.prefWidthProperty().bind(mainPane.widthProperty());
             Patient p= new Patient();
             PatientDetailsController controller=loader.<PatientDetailsController>getController();
             controller.initComponents(p,mainPane, PatientDetailsController.paneType.NEW_PATIENT,null,null);
        	
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }

    @FXML
    void viewAllClicked(ActionEvent event) {
    	 try {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
         GridPane patientsView= (GridPane) loader.load();
         mainPane.getChildren().clear();
         mainPane.getChildren().add(patientsView);
         patientsView.prefHeightProperty().bind(mainPane.heightProperty());
         patientsView.prefWidthProperty().bind(mainPane.widthProperty());
         
         PatientsViewPaneController controller=loader.<PatientsViewPaneController>getController();
         controller.initComponents(mainPane, "receptionist",null,null);
         
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void initComponents(User user) {
    	this.user=user;
    }
    
    @FXML
	void signOutClicked(ActionEvent event) {
		Alert a= new Alert(AlertType.CONFIRMATION,"Do you want to sign out?",
	 				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
	    a.setTitle("Exit");
	    a.setHeaderText("Sign out");
	    String confirmation = a.showAndWait().get().getText();
	 	if (confirmation.equals("Yes")) {
	 		try {
	 		Parent loginScene= (Parent) FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
	 		Stage stage= (Stage) receptionistPane.getScene().getWindow();
	 		Scene scene= new Scene(loginScene,600,405);
	 		stage.centerOnScreen();
	 		stage.setResizable(false);
	 		stage.setScene(scene);
	 		}catch(Exception ex) {
	 			ex.printStackTrace();
	 		}
	 	}
	}

	@FXML
	void myProfileClicked(ActionEvent event) {
		try {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("myProfilePane.fxml"));
		GridPane profilePane=(GridPane) loader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(profilePane);
		profilePane.prefHeightProperty().bind(mainPane.heightProperty());
		profilePane.prefWidthProperty().bind(mainPane.widthProperty());
		
		MyProfilePaneController paneController= loader.<MyProfilePaneController>getController();
		paneController.initComponents(user);
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void loadPatientClicked(ActionEvent event) {
		//We register a new patient from an XML file
		
		try {
		FileChooser fileChooser= new FileChooser();
		fileChooser.setTitle("Load Patient XML");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files","*.xml"));
		File patientFile= fileChooser.showOpenDialog(mainPane.getScene().getWindow());
		
		if(patientFile!=null) {
		Patient patient= XmlPatient.unmarshal(patientFile);
		
		 FXMLLoader loader=new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
         GridPane patientsView= (GridPane) loader.load();
         mainPane.getChildren().clear();
         mainPane.getChildren().add(patientsView);
         patientsView.prefHeightProperty().bind(mainPane.heightProperty());
         patientsView.prefWidthProperty().bind(mainPane.widthProperty());
         PatientDetailsController controller=loader.<PatientDetailsController>getController();
         controller.initComponents(patient,mainPane, PatientDetailsController.paneType.NEW_PATIENT_XML,null,null);
		}
		}catch(Exception ex) {
			Alert alert= new Alert(AlertType.ERROR);
			alert.setHeaderText("Error loading");
			alert.setContentText("The file could not be loaded");
			alert.showAndWait();
		
		}
		
	}


}
