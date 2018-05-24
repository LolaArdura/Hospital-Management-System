package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import jdbcManager.JDBCUserController;
import model.User;
 
public class MyProfilePaneController {
	private User user;

    @FXML
    private GridPane userPane;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label permissionLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField permissionTextField;

    @FXML
    private Button okButton1;

    @FXML
    void okButtonClicked(ActionEvent event) {
    	String username= usernameTextField.getText();
    	if(username.trim().equals("")){
    			Alert alert= new Alert(AlertType.ERROR);
    			alert.setHeaderText("No username");
    			alert.setContentText("A username needs to be specified");
    			alert.showAndWait();
    			usernameTextField.setText(user.getUsername());
    			usernameTextField.requestFocus();
    		}
    	else {
    		String password=passwordTextField.getText();
    		if(password.trim().equals("")) {
    			Alert alert=new Alert(AlertType.ERROR);
    			alert.setHeaderText("No password");
    			alert.setContentText("A password needs to be specified");
    			alert.showAndWait();
    			passwordTextField.setText(user.getPassword());
    			passwordTextField.requestFocus();
    		}
    		else {
    			user.setUsername(username);
    			user.setPassword(password);
    			try {
    			JDBCUserController.getUserController().updateUser(user);
    			}catch(Exception ex) {
    				Alert alert=new Alert(AlertType.ERROR);
        			alert.setHeaderText("Invalid username");
        			alert.setContentText("This username is already been used");
        			alert.showAndWait();
    			}
    		}
    	}
    	}
    
    
    public void initComponents(User user) {
    	this.user=user;
    	usernameTextField.setText(user.getUsername());
    	passwordTextField.setText(user.getPassword());
    	permissionTextField.setText(user.getTypeOfUser().toString().toLowerCase());
    	permissionTextField.setEditable(false);
    }

}
