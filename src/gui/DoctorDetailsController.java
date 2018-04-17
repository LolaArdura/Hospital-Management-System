package gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Doctor;

public class DoctorDetailsController {

    @FXML
    private GridPane detailsPane;
    
    @FXML
    private GridPane labelsPane;
    
    @FXML
    private Button browseButton;
    
    @FXML
    private Button okButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label scheduleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField specialtyTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private ImageView photo;
    
    private Doctor doctor;
    
    private Pane mainPane;
    
    public void initComponents (Doctor doctor, Pane mainPane) {
    	this.doctor=doctor;
    	this.mainPane=mainPane;
    	nameTextField.setText(doctor.getName());
    	specialtyTextField.setText(doctor.getSpeciality());
    	scheduleTextField.setText(doctor.getSchedule());
    	if(doctor.getPhoto()!=null) {
    	photo.setImage(new Image(new ByteArrayInputStream(doctor.getPhoto())));
    	}
    	else {
    	   File defaultImage=new File(System.getProperty("user.dir")+"Doctors-Male-Avatar.png");
    	   try {
			photo.setImage(SwingFXUtils.toFXImage(ImageIO.read(defaultImage), null));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	}
    }
    
    public void browseButtonClicked(ActionEvent ev) {
    	 FileChooser fileChooser= new FileChooser();
    	   fileChooser.setTitle("Choose an image");
    	   fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.jpg"));
    	   File file=fileChooser.showOpenDialog(detailsPane.getScene().getWindow());
    	   try {
    	     photo.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
    	   }catch(Exception ex) {
    		   ex.printStackTrace();
    	   }
    }
    
    @FXML
    public void okButtonClicked(ActionEvent ev) {
    	
    }
    

}
