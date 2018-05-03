package gui.receptionist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ReceptionistPaneController {

    @FXML
    private BorderPane receptionistPane;

    @FXML
    private MenuItem myProfileItem;

    @FXML
    private MenuItem signOutItem;

    @FXML
    private VBox buttonsVBox;

    @FXML
    private Label menuLabel;

    @FXML
    private Accordion accordionContainer;

    @FXML
    private TitledPane patientsTitledPane;

    @FXML
    private VBox patientsVBox;

    @FXML
    private Button viewAllButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Pane mainPane;

    @FXML
	void registrationButtonClicked(ActionEvent event) {

    }

    @FXML
    void viewAllClicked(ActionEvent event) {

    }

}
