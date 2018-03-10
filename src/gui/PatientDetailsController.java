package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Patient;

public class PatientDetailsController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label sexLabel;

    @FXML
    private Label admissionLabel;

    @FXML
    private TextArea medicalConditionArea;

    @FXML
    private Button okButton;
    
    private Patient patient;

    @FXML
    public void okButtonClicked(ActionEvent event) {

    }
    
    public void initComponents (Patient patient) {
    	this.patient=patient;
    	nameLabel.setText(patient.getName());
		birthLabel.setText(""+patient.getDob().getDate()+"-"+patient.getDob().getMonth()+1+"-"+patient.getDob().getYear());
		sexLabel.setText(patient.getGender().name().toLowerCase());
		admissionLabel.setText(""+patient.getDateAdmission().getDate()+
				"-"+patient.getDateAdmission().getMonth()+1+"-"+patient.getDateAdmission().getYear());
		medicalConditionArea.setText(patient.getMedicalCondition());
    }
    
    
}
