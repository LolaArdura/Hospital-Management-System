package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Patient;
import model.User;

public class ReceptionistPaneController {

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
             controller.initComponents(p,mainPane, PatientDetailsController.paneType.NEW_PATIENT);
        	
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
         controller.initComponents(mainPane, User.userType.RECEPTIONIST);
         
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

}
