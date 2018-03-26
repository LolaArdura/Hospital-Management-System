package gui;


import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Nurse;

public class NurseDetailsController {

    @FXML
    private GridPane detailsPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label scheduleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private ImageView photo;
    
    private Nurse nurse;
    
    public void initComponents(Nurse nurse) {
    	this.nurse=nurse;
    	nameTextField.setText(nurse.getName());
    	roleTextField.setText(nurse.getRole());
    	scheduleTextField.setText(nurse.getSchedule());
    	photo.setImage(new Image(new ByteArrayInputStream(nurse.getPhoto())));
    }

}

