package gui;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Doctor;
import model.User;

public class DoctorMainPaneController{
		
	    private User user;
	
	    private Doctor doctor;
	
	    @FXML
	    private BorderPane doctorsPane;
	    
	    @FXML
	    private MenuButton profileMenuButton;

	    @FXML
	    private MenuItem myProfileItem;

	    @FXML
	    private MenuItem signOutItem;

	    @FXML
	    private Pane mainPane;
	    
	    public void initComponents (User user, Doctor doctor) {
	    	this.user=user;
	    	this.doctor=doctor;
	    	
	    	try {
				FXMLLoader loader= new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
				GridPane patientsPane= (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(patientsPane);
				patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
				patientsPane.prefWidthProperty().bind(mainPane.widthProperty());
				
				PatientsViewPaneController controller= loader.<PatientsViewPaneController>getController();
				controller.initComponents(mainPane, user.getTypeOfUser(), this.doctor, null);
				
				}catch(Exception ex) {
					ex.printStackTrace();
				}
	    }
	    
	    @FXML
		void signOutClicked(ActionEvent event) {
			Alert a= new Alert(AlertType.CONFIRMATION,"Do you want to sign out?",
		 				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
		    a.setTitle("Exit");
		    a.setHeaderText("Sign out");
		    String confirmation = a.showAndWait().get().getText();
		 	if (confirmation.equals("Yes")) {
		 		try {
		 		Parent loginScene= (Parent) FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		 		Stage stage= (Stage) doctorsPane.getScene().getWindow();
		 		Scene scene= new Scene(loginScene,600,405);
		 		stage.centerOnScreen();
		 		stage.setResizable(false);
		 		stage.setScene(scene);
		 		}catch(Exception ex) {
		 			ex.printStackTrace();
		 		}
		 	}
		}

		@FXML
		void myProfileClicked(ActionEvent event) {
			try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("myProfilePane.fxml"));
			GridPane profilePane=(GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(profilePane);
			profilePane.prefHeightProperty().bind(mainPane.heightProperty());
			profilePane.prefWidthProperty().bind(mainPane.widthProperty());
			
			MyProfilePaneController paneController= loader.<MyProfilePaneController>getController();
			paneController.initComponents(user);
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}


}
