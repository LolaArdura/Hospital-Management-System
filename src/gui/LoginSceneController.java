package gui;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdbcManager.JDBCDoctorController;
import jdbcManager.JDBCUserController;
import model.Doctor;
import model.User;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.glass.ui.Screen;

import javafx.fxml.FXMLLoader;
import interfaces.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginSceneController implements Initializable{
    @FXML
    private TextField usernameTextField;
    
    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private Button arrowButton;
    
    @FXML
    private Label invalidUserLabel;
    
    @FXML
    private AnchorPane loginScene;
    
    
    
    public void arrowButtonClicked(ActionEvent event) {
    	try {
    	String username=usernameTextField.getText();
    	String password=passwordTextField.getText();
    	User user= new User(username,password);
    	
    	UserInterface controller= JDBCUserController.getUserController();
    	user=controller.validateUser(user);
    	if(!(user.getId().equals(null))) {
    		invalidUserLabel.setVisible(true);
    	}
    	else {
    		User.userType permission=user.getTypeOfUser();
    		if(permission.equals(User.userType.ADMIN)) {
    			FXMLLoader loader= new FXMLLoader(getClass().getResource("AdminMainScene.fxml"));
    			Parent adminScene= (Parent) loader.load();
    			AdminMainSceneController adminController=loader.<AdminMainSceneController>getController();
    			adminController.initComponents(user);	
    			
    			Stage stage= (Stage)(loginScene.getScene().getWindow()); //(Node) event.getResource()).getScene().getWindow();
                Scene scene=new Scene(adminScene, Screen.getMainScreen().getWidth(),Screen.getMainScreen().getHeight());
				stage.setScene(scene);
    		}
    		else {
    			if(permission.equals(User.userType.RECEPTIONIST)) {
    				FXMLLoader loader= new FXMLLoader(getClass().getResource("ReceptionistMainPane.fxml"));
        			Parent receptionistScene= (Parent) loader.load();
        			ReceptionistPaneController receptionistController=loader.<ReceptionistPaneController>getController();
        			receptionistController.initComponents(user);
        			
        			Stage stage= (Stage) loginScene.getScene().getWindow();
                    Scene scene=new Scene(receptionistScene, Screen.getMainScreen().getWidth(),Screen.getMainScreen().getHeight());
    				stage.setScene(scene);
    			}
    			else {
    				if(permission.equals(User.userType.DOCTOR)) {
    					try {
    						TextInputDialog idDialog = new TextInputDialog("");
    						idDialog.setTitle("");
							idDialog.setHeaderText("Enter your doctor id");
							idDialog.setContentText("Doctor id:");
							Optional<String> idTyped = idDialog.showAndWait();
							if (idTyped.isPresent()) {
								Integer id=Integer.parseInt(idTyped.get());
								Doctor doctor= JDBCDoctorController.getDoctorController().searchDoctorById(id);
								if(doctor!=null) {
								FXMLLoader loader= new FXMLLoader(getClass().getResource("DoctorMainPane.fxml"));
								Parent doctorMainPane=(Parent) loader.load();
								DoctorMainPaneController doctorController=loader.<DoctorMainPaneController>getController();
								doctorController.initComponents(user, doctor);
								Stage stage= (Stage) loginScene.getScene().getWindow();
			                    Scene scene=new Scene(doctorMainPane, Screen.getMainScreen().getWidth(),Screen.getMainScreen().getHeight());
			    				stage.setScene(scene);
								}
								else {
									Alert a= new Alert(AlertType.ERROR);
		    						a.setContentText("If you forgot your id, an admin will give it to you");
		    						a.setHeaderText("No doctor with the specified id");
		    						a.showAndWait();
								}
							}
    						
    					}catch(NumberFormatException ex) {
    						Alert a= new Alert(AlertType.ERROR);
    						a.setContentText("If you forgot your id, an admin will give it to you");
    						a.setHeaderText("Id needs to be a number");
    						a.showAndWait();
    					}
    				}
    			}
    		}
    		
    		
    		
    			
    		
    	}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		invalidUserLabel.setVisible(false);	
	}
    
    
}
