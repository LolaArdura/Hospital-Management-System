package gui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminMainSceneController {

	@FXML
	private VBox menuVBox;

	@FXML
	private Label menuLabel;

	@FXML
	private MenuButton roomsMenuButton;

	@FXML
	private MenuItem roomCostsItem;

	@FXML
	private MenuItem roomManagementItem;
	
	@FXML
	private Pane buttonPanel;

	@FXML
	private Pane mainPane;
	
	@FXML
	private Button patientsButton;

	public void patientsButtonClicked(ActionEvent event) {
		try {
		Pane patientsPane = (Pane) FXMLLoader.load(getClass().getResource("PatientsViewPane.fxml"));
		mainPane.getChildren().clear();
		mainPane.getChildren().add(patientsPane);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

