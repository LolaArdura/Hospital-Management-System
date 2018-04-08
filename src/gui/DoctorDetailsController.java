package gui;

import java.io.ByteArrayInputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Doctor;

public class DoctorDetailsController {

    @FXML
    private GridPane detailsPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label scheduleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField specialityTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private ImageView photo;
    
    private Doctor doctor;
    
    public void initComponents (Doctor doctor) {
    	this.doctor=doctor;
    	nameTextField.setText(doctor.getName());
    	specialityTextField.setText(doctor.getSpeciality());
    	scheduleTextField.setText(doctor.getSchedule());
    	if(doctor.getPhoto()!=null) {
    	photo.setImage(new Image(new ByteArrayInputStream(doctor.getPhoto())));
    	}
    }
    
   
    

}
