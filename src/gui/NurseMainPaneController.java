package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.User;
import model.Nurse;

public class NurseMainPaneController {
	private User user;
	private Nurse nurse;

    @FXML
    private BorderPane nursesPane;
    
    @FXML
    private MenuButton profileMenuButton;

    @FXML
    private MenuItem myProfileItem;

    @FXML
    private MenuItem signOutItem;

    @FXML
    private Pane mainPane;

    @FXML
    void myProfileClicked(ActionEvent event) {

    }

    @FXML
    void signOutClicked(ActionEvent event) {

    }
    
    public void initComponents(User user,Nurse nurse) {
    	this.user=user;
    	this.nurse=nurse;
    	
    	try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
			GridPane patientsPane= (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(patientsPane);
			patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
			patientsPane.prefWidthProperty().bind(mainPane.widthProperty());
			
			PatientsViewPaneController controller= loader.<PatientsViewPaneController>getController();
			controller.initComponents(mainPane, user.getTypeOfUser(), null, this.nurse);
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}
    }

}

