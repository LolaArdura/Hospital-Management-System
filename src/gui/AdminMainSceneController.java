package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminMainSceneController{
	
	@FXML
	private VBox buttonsVBox;
	
	@FXML
	private BorderPane adminPane;

    @FXML
    private MenuItem myProfileItem;

    @FXML
    private MenuItem signOutItem;

    @FXML
    private Button patientsButton;

    @FXML
    private Button doctorsButton;

    @FXML
    private Button nurseButton;

    @FXML
    private Button userButton;

    @FXML
    private MenuButton roomsMenuButton;

    @FXML
    private MenuItem roomManagementItem;

    @FXML
    private MenuItem roomCostsItem;

    @FXML
    private Pane mainPane;


	public void patientsButtonClicked(ActionEvent event) {
		try {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
		GridPane patientsPane = (GridPane) loader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(patientsPane);
		patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
		patientsPane.prefWidthProperty().bind(mainPane.widthProperty());
	    
		PatientsViewPaneController controller=loader.<PatientsViewPaneController>getController();
		controller.initComponents(mainPane);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
    @FXML
    void nursesButtonClicked(ActionEvent event) {
       try {
       mainPane.getChildren().clear();
       GridPane nursesView = (GridPane)FXMLLoader.load(getClass().getResource("NursesPane.fxml"));
       mainPane.getChildren().add(nursesView);
       nursesView.prefHeightProperty().bind(mainPane.heightProperty());
       nursesView.prefWidthProperty().bind(mainPane.widthProperty());
       }catch(Exception ex) {
    	   ex.printStackTrace();
       }
    }
    
    @FXML
    public void doctorsButtonClicked(ActionEvent event) {
    	try {
    		mainPane.getChildren().clear();
    		FXMLLoader loader= new FXMLLoader(getClass().getResource("DoctorsViewPane.fxml"));
    		GridPane doctorsView=(GridPane)loader.load();
    		mainPane.getChildren().add(doctorsView);
    		doctorsView.prefHeightProperty().bind(mainPane.heightProperty());
    		doctorsView.prefWidthProperty().bind(mainPane.widthProperty());
    		DoctorsPaneController controller= loader.<DoctorsPaneController>getController();
    		controller.initComponents(mainPane);
    		
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
	

}

