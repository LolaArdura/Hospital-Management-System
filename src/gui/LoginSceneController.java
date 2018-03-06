package gui;
import javafx.scene.control.*;
import javafx.fxml.FXML;

public class LoginSceneController{
    @FXML
    private TextField usernameTextField;
    
    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private Button arrowButton;
    
    @FXML
    private Label invalidUserLabel;
    
    public void arrowButtonClicked() {
    	String username=usernameTextField.getText();
    	String password=passwordTextField.getText();
    	
    	System.out.println("FUNCIONA");
    
    }
    
    
}
