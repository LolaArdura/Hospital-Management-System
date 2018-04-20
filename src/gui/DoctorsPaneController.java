package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import jdbcManager.JDBCDoctorController;
import model.Doctor;

public class DoctorsPaneController implements Initializable{
	
	private Pane mainPane;

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
    private ListView<Doctor> doctorsList;

    @FXML
    private Button viewDetailsButton;

    @FXML
    private Button addButton;
    
    @FXML 
    private JFXTextField floatTextField;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	setDoctors();
    	doctorsList.setCellFactory(new Callback<ListView<Doctor>, ListCell<Doctor>>(){
			@Override
			public ListCell<Doctor> call(ListView<Doctor> doc) {
				ListCell<Doctor> cell= new ListCell<Doctor>() {
			
					@Override
					protected void updateItem (Doctor item,boolean bln) {
						super.updateItem(item, bln);
						if(item!=null) {
							setText(item.getName());
						}
					}
				};
				return cell;
			}
    		
    });
   }
    
    public void initComponents(Pane mainPane) {
    	this.mainPane=mainPane;
    }
    
    @FXML
    public void addNewButtonClicked(ActionEvent event) {

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
  			    	JDBCDoctorController.getDoctorController().insertDoctor(doctor);
  			    	
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
    public void browseButtonClicked(ActionEvent event) {
       FileChooser fileChooser= new FileChooser();
  	   fileChooser.setTitle("Choose an image");
  	   fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.jpg"));
  	   File file=fileChooser.showOpenDialog(doctorsPane.getScene().getWindow());
  	   if(file!=null) {
  		 pictureTextField.setText(file.getAbsolutePath());
  	   }
    }
    
    @FXML
    public void viewDetailsClicked(ActionEvent event) {
          Doctor doctor= doctorsList.getSelectionModel().getSelectedItem();
          if(doctor!=null) {
        	  try {
        	  FXMLLoader loader= new FXMLLoader (getClass().getResource("DoctorDetails.fxml"));
        	  GridPane doctorDetails=(GridPane) loader.load();
        	  mainPane.getChildren().clear();
        	  mainPane.getChildren().add(doctorDetails);
        	  doctorDetails.prefHeightProperty().bind(mainPane.heightProperty());
        	  doctorDetails.prefWidthProperty().bind(mainPane.widthProperty());
        	  DoctorDetailsController controller= loader.<DoctorDetailsController>getController();
        	  controller.initComponents(doctor, mainPane);
        	  }catch(Exception ex) {
        		  ex.printStackTrace();
        	  }
          }
        
    }
    
    private void setDoctors() {
    	try {
    	ObservableList<Doctor> doctors= FXCollections.observableArrayList();
	    //doctors.addAll(JDBCDoctorController.getDoctorController().getAllDoctors());
    	
	    doctorsList.setItems(doctors);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}