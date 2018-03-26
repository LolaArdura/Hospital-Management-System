package gui;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import jdbcManager.NurseController;
import model.Nurse;

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
		try {
			nursesScrollPane.setPannable(true);
		    LinkedList<Nurse> nurses= (LinkedList<Nurse>) NurseController.getNurseController().getAllNurses();
		    for(Nurse nurse:nurses){
		    	FXMLLoader loader=new FXMLLoader (getClass().getResource("NurseDetailsPane.fxml"));
		    	GridPane nurseDetails=(GridPane)loader.load();
		    	nurseDetails.prefWidthProperty().bind(nursesFlowPane.widthProperty());
		    	nursesFlowPane.getChildren().add(nurseDetails);
		    	NurseDetailsController controller= loader.<NurseDetailsController>getController();
		    	controller.initComponents(nurse);
		    }
				
			} catch (Exception e) {
				e.printStackTrace();	    
		
	      }
	}

}


