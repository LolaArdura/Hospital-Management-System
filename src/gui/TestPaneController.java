package gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Patient;
import javafx.scene.control.Button;

public class TestPaneController {
	
	    @FXML
	    private Button testButton;
        
	    private Patient patient;
	    
	    public void initComponents(Patient patient) {
	    	this.patient=patient;
	    }
	    
	    public void testButtonClicked(ActionEvent event) {
	    	System.out.print(patient);
	    }
}

