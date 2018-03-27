package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import jdbcManager.DoctorController;
import model.Doctor;


public class DoctorsPaneController implements Initializable{

    @FXML
    private GridPane doctorsPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private TextField specialityTextField;

    @FXML
    private TextField pictureTextField;

    @FXML
    private Button browseButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ScrollPane doctorsScrollPane;

    @FXML
    private FlowPane doctorsFlowPane;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		doctorsFlowPane.prefWidthProperty().bind(doctorsScrollPane.widthProperty());
		doctorsFlowPane.prefHeightProperty().bind(doctorsScrollPane.heightProperty());
	    setDoctors();		
}

    @FXML
    void addNewButtonClicked(ActionEvent event) {
       String name= nameTextField.getText();
  	   Alert alert= new Alert(AlertType.ERROR);
  	   if(name.equals("")) {
  		   alert.setTitle("ERROR");
  		   alert.setHeaderText("No name");
  		   alert.setContentText("A name needs to be specified for the new doctor");
  		   alert.showAndWait();
  		   nameTextField.requestFocus();
  	   }
  	   else {
  		   String speciality=specialityTextField.getText();
  		   if(speciality.equals("")) {
  			   alert.setTitle("ERROR");
  			   alert.setHeaderText("No speciality");
  			   alert.setContentText("A speciality needs to be specified for the new doctor");
  			   alert.showAndWait();
  			   specialityTextField.requestFocus();
  		   }
  		   else {
  			   String schedule=scheduleTextField.getText();
  			   if(schedule.equals("")) {
  				   alert.setTitle("ERROR");
  				   alert.setHeaderText("No schedule");
  				   alert.setContentText("Schedule needs to be specified for the new doctor");
  				   alert.showAndWait();
  				   scheduleTextField.requestFocus();
  			   }
  			   else {
  				   try {
  					Doctor doctor;
  					String path=pictureTextField.getText();
  					if(!path.equals("")) {
  					   Path filePath=Paths.get(path);
  					   byte[] photo=Files.readAllBytes(filePath);
  			           doctor=new Doctor(name,photo,schedule,speciality);
  					}
  					
  					else {
  					   doctor= new Doctor(name,schedule,speciality);
  					}
  								
  					//We insert into the database the new doctor
  			    	DoctorController.getDoctorController().insertDoctorWithoutId(doctor);
  			    	
  			    	//We show the new doctor
  			    	setDoctors();
  							    	
  			    	//We clear the text fields
  			    	nameTextField.clear();
  			    	scheduleTextField.clear();
  			    	specialityTextField.clear();
  			    	pictureTextField.clear();
  					
  				} catch (IOException e) {
  					e.printStackTrace();
  				} catch (Exception e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  				 
  			   }
  		   }
  	   }

    }

    @FXML
    void browseButtonClicked(ActionEvent event) {
       FileChooser fileChooser= new FileChooser();
  	   fileChooser.setTitle("Choose an image");
  	   fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.jpg"));
  	   File file=fileChooser.showOpenDialog(doctorsPane.getScene().getWindow());
  	   if(file!=null) {
  		 pictureTextField.setText(file.getAbsolutePath());
  	   }
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
       String name= nameTextField.getText();
   	   Alert alert= new Alert(AlertType.ERROR);
   	   if(name.equals("")) {
   		   alert.setTitle("ERROR");
   		   alert.setHeaderText("No name");
   		   alert.setContentText("A name needs to be specified");
   		   alert.showAndWait();
   		   nameTextField.requestFocus();
   	   }
   	   else {
   		   String speciality=specialityTextField.getText();
   		   if(speciality.equals("")) {
   			   alert.setTitle("ERROR");
   			   alert.setHeaderText("No speciality");
   			   alert.setContentText("A speciality needs to be specified");
   			   alert.showAndWait();
   			   specialityTextField.requestFocus();
   		   }
   		   else {
   			   String schedule=scheduleTextField.getText();
   			   if(schedule.equals("")) {
   				   alert.setTitle("ERROR");
   				   alert.setHeaderText("No schedule");
   				   alert.setContentText("Schedule needs to be specified");
   				   alert.showAndWait();
   				   scheduleTextField.requestFocus();
   			   }
   			   else {
          
   					Doctor doctor= new Doctor(name,schedule,speciality);
   					
   								
   					//We insert into the database the new doctor
   			    	try {
						DoctorController.getDoctorController().deleteDoctorWithoutId(doctor);
					} catch (Exception e) {
						e.printStackTrace();
					}
   			    	
   			    	//We show the new doctor
   			    	setDoctors();
   							    	
   			    	//We clear the text fields
   			    	nameTextField.clear();
   			    	scheduleTextField.clear();
   			    	specialityTextField.clear();
   			    	pictureTextField.clear();
   			   }
   		  	 }
   	      }			
   	   }	
  
    public void setDoctors() {
 	   doctorsFlowPane.getChildren().clear();
 	   LinkedList<Doctor> doctors;
 	try {
 		doctors=(LinkedList<Doctor>) DoctorController.getDoctorController().getAllDoctors();
 	    for(Doctor doctor:doctors){
 	    	FXMLLoader loader=new FXMLLoader (getClass().getResource("DoctorDetailsPane.fxml"));
 	    	GridPane doctorDetails=(GridPane)loader.load();
 	    	doctorDetails.prefWidthProperty().bind(doctorsScrollPane.widthProperty());
 	    	doctorsFlowPane.getChildren().add(doctorDetails);
 	    	DoctorDetailsController controller= loader.<DoctorDetailsController>getController();
 	    	controller.initComponents(doctor);
 	    }
    } catch (Exception e) {
 		e.printStackTrace();
 	}
 	
    }

}

