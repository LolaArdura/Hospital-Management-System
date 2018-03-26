package gui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import jdbcManager.NurseController;
import model.Nurse;
import sun.misc.IOUtils;


public class NursesViewPaneController implements Initializable {

    @FXML
    private GridPane nursesPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private TextField pictureTextField;

    @FXML
    private Button browseButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private ScrollPane nursesScrollPane;
    
    @FXML
    private FlowPane nursesFlowPane;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			nursesFlowPane.prefWidthProperty().bind(nursesScrollPane.widthProperty());
			nursesFlowPane.prefHeightProperty().bind(nursesScrollPane.heightProperty());
		    setNurses();		
	}
	
   @FXML
   public void addNewButtonClicked(ActionEvent event) {
	   String name= nameTextField.getText();
	   Alert alert= new Alert(AlertType.ERROR);
	   if(name.equals("")) {
		   alert.setTitle("ERROR");
		   alert.setHeaderText("No name");
		   alert.setContentText("A name needs to be specified for the new nurse");
		   alert.showAndWait();
		   nameTextField.requestFocus();
	   }
	   else {
		   String role=roleTextField.getText();
		   if(role.equals("")) {
			   alert.setTitle("ERROR");
			   alert.setHeaderText("No role");
			   alert.setContentText("A role needs to be specified for the new nurse");
			   alert.showAndWait();
			   roleTextField.requestFocus();
		   }
		   else {
			   String schedule=scheduleTextField.getText();
			   if(schedule.equals("")) {
				   alert.setTitle("ERROR");
				   alert.setHeaderText("No schedule");
				   alert.setContentText("Schedule needs to be specified for the new nurse");
				   alert.showAndWait();
				   scheduleTextField.requestFocus();
			   }
			   else {
				   try {
					Nurse nurse;
					int n;
					String path=pictureTextField.getText();
					if(!path.equals("")) {
					  URL url=new URL(path);
					  InputStream inputStream=url.openStream();
					  byte[] array=new byte[4096];
					  ByteArrayOutputStream bytes=new ByteArrayOutputStream();
			          while((n=inputStream.read(array))>0) {
			        	  bytes.write(array,0,n);
			          }
			          byte[] photo=bytes.toByteArray();
			           nurse=new Nurse(name,photo,schedule,role);
					}
					
					else {
					   nurse= new Nurse(name,schedule,role);
					}
								
					//We insert into the database the new nurse
			    	NurseController.getNurseController().insertNurse(nurse);
			    	
			    	//We show the new nurse
			    	setNurses();
							    	
			    	//We clear the text fields
			    	nameTextField.clear();
			    	scheduleTextField.clear();
			    	roleTextField.clear();
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
   
   public void setNurses() {
	   nursesFlowPane.getChildren().clear();
	   LinkedList<Nurse> nurses;
	try {
		nurses = (LinkedList<Nurse>) NurseController.getNurseController().getAllNurses();
	    for(Nurse nurse:nurses){
	    	FXMLLoader loader=new FXMLLoader (getClass().getResource("NurseDetailsPane.fxml"));
	    	GridPane nurseDetails=(GridPane)loader.load();
	    	nurseDetails.prefWidthProperty().bind(nursesFlowPane.widthProperty());
	    	nursesFlowPane.getChildren().add(nurseDetails);
	    	NurseDetailsController controller= loader.<NurseDetailsController>getController();
	    	controller.initComponents(nurse);
	    }
   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   }

}


