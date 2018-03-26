package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.Patient;

import java.net.URL;
import java.util.ResourceBundle;



public class PatientDetailsController implements Initializable {
	
	@FXML
	private Pane adminPane;
	
	@FXML
    private GridPane detailsPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField birthTextField;

    @FXML
    private TextField sexTextField;

    @FXML
    private TextField admissionTextField;

    @FXML
    private Label treatmentLabel;

    @FXML
    private Button treatmentButton;

    @FXML
    private TextArea medicalConditionArea;

    @FXML
    private Button okButton;
    
    private Patient patient;

    @FXML
    public void okButtonClicked(ActionEvent event) {
    	try {
    	FXMLLoader loader= new FXMLLoader (getClass().getResource("PatientsViewPane.fxml"));
    	GridPane patientsViewPane = (GridPane)loader.load();
        adminPane.getChildren().clear();
     	adminPane.getChildren().add(patientsViewPane);
    	patientsViewPane.prefHeightProperty().bind(adminPane.heightProperty());
    	patientsViewPane.prefWidthProperty().bind(adminPane.widthProperty());
    	
    	PatientsViewPaneController controller= loader.<PatientsViewPaneController>getController();
    	controller.initComponents(adminPane);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}

    }
    
    public void initComponents (Patient patient,Pane adminPane) {
    	this.patient=patient;
    	nameTextField.setText(patient.getName());
		birthTextField.setText(""+patient.getDob().getDate()+"-"+patient.getDob().getMonth()+1+"-"+patient.getDob().getYear());
		sexTextField.setText(patient.getGender().name().toLowerCase());
		admissionTextField.setText(""+patient.getDateAdmission().getDate()+
				"-"+patient.getDateAdmission().getMonth()+1+"-"+patient.getDateAdmission().getYear());
		medicalConditionArea.setText(patient.getMedicalCondition());
		this.adminPane=adminPane;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameTextField.setEditable(false);
		birthTextField.setEditable(false);
		sexTextField.setEditable(false);
		admissionTextField.setEditable(false);
		treatmentLabel.setVisible(false);
		treatmentButton.setVisible(false);
		medicalConditionArea.setEditable(false);			
	}
	
    
}
