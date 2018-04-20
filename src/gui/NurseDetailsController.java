package gui;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Nurse;

public class NurseDetailsController {
	private Pane mainPane;

    @FXML
    private GridPane detailsPane;

    @FXML
    private ImageView photo;

    @FXML
    private Button browseButton;

    @FXML
    private GridPane labelsPane;

    @FXML
    private Label scheduleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;
    
    private Image defaultImage;
    
    private Nurse nurse;
    
    public void initComponents(Nurse nurse,Pane mainPane) {
    	this.nurse=nurse;
    	this.mainPane=mainPane;
    	nameTextField.setText(nurse.getName());
    	roleTextField.setText(nurse.getRole());
    	scheduleTextField.setText(nurse.getSchedule());
    	if(nurse.getPhoto()!=null) {
    	photo.setImage(new Image(new ByteArrayInputStream(nurse.getPhoto())));
    	}
    	else {
    	   try {
    		defaultImage=new Image("/Doctors-Male-Avatar2.png");
			photo.setImage(defaultImage);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	}
    }

    @FXML
    void browseButtonClicked(ActionEvent event) {
       FileChooser fileChooser= new FileChooser();
  	   fileChooser.setTitle("Choose an image");
  	   fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.jpg"));
  	   File file=fileChooser.showOpenDialog(detailsPane.getScene().getWindow());
  	   try {
  		 if(file!=null) {
  	     photo.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
  		 }
  	   }catch(Exception ex) {
  		   ex.printStackTrace();
  	   }

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
    	try {
        	FXMLLoader loader= new FXMLLoader(getClass().getResource("NursesViewPane.fxml"));
        	GridPane nursesPane =(GridPane) loader.load();
        	mainPane.getChildren().clear();
        	mainPane.getChildren().add(nursesPane);
        	nursesPane.prefHeightProperty().bind(mainPane.heightProperty());
        	nursesPane.prefWidthProperty().bind(mainPane.widthProperty());
        	NursesViewPaneController controller=loader.<NursesViewPaneController>getController();
        	controller.initComponents(mainPane);
        	
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}

    }

    @FXML
    void okButtonClicked(ActionEvent event) {

    }

}


