package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.User;

public class AdminMainSceneController {

	@FXML
	private BorderPane adminPane;

	@FXML
	private MenuItem myProfileItem;

	@FXML
	private MenuItem signOutItem;

	@FXML
	private VBox buttonsVBox;

	@FXML
	private Accordion accordionContainer;

	@FXML
	private TitledPane patientsTitledPane;

	@FXML
	private Button patientsButton;

	@FXML
	private TitledPane doctorsTitledPane;

	@FXML
	private Button doctorsButton;

	@FXML
	private TitledPane nursesTitledPane;

	@FXML
	private VBox nursesVBox;

	@FXML
	private Button nurseManagementButton;

	@FXML
	private Button patientAssignmentButton;

	@FXML
	private TitledPane usersTitledPane;

	@FXML
	private Button usersButton;

	@FXML
	private TitledPane roomsTitledPane;

	@FXML
	private VBox roomsVBox;

	@FXML
	private Button roomManagementButton;

	@FXML
	private Button roomCostsButton;

	@FXML
	private Pane mainPane;

	public void patientsButtonClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
			GridPane patientsPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(patientsPane);
			patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
			patientsPane.prefWidthProperty().bind(mainPane.widthProperty());

			PatientsViewPaneController controller = loader.<PatientsViewPaneController>getController();
			controller.initComponents(mainPane,User.userType.ADMIN);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void nurseManagementClicked(ActionEvent event) {
		try {
			mainPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NursesPane.fxml"));
			GridPane nursesView = (GridPane) loader.load();
			mainPane.getChildren().add(nursesView);
			nursesView.prefHeightProperty().bind(mainPane.heightProperty());
			nursesView.prefWidthProperty().bind(mainPane.widthProperty());
			NursesViewPaneController controller = loader.<NursesViewPaneController>getController();
			controller.initComponents(mainPane, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void doctorsButtonClicked(ActionEvent event) {
		try {
			mainPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorsViewPane.fxml"));
			GridPane doctorsView = (GridPane) loader.load();
			mainPane.getChildren().add(doctorsView);
			doctorsView.prefHeightProperty().bind(mainPane.heightProperty());
			doctorsView.prefWidthProperty().bind(mainPane.widthProperty());
			DoctorsPaneController controller = loader.<DoctorsPaneController>getController();
			controller.initComponents(mainPane);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void patientAssignmentClicked(ActionEvent event) {
		try {
			mainPane.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NursesPane.fxml"));
			GridPane nursesView = (GridPane) loader.load();
			mainPane.getChildren().add(nursesView);
			nursesView.prefHeightProperty().bind(mainPane.heightProperty());
			nursesView.prefWidthProperty().bind(mainPane.widthProperty());
			NursesViewPaneController controller = loader.<NursesViewPaneController>getController();
			controller.initComponents(mainPane, false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void roomCostsClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomCosts.fxml"));
			GridPane roomCostPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(roomCostPane);
			roomCostPane.prefHeightProperty().bind(mainPane.heightProperty());
			roomCostPane.prefWidthProperty().bind(mainPane.widthProperty());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void roomManagementClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomManagement.fxml"));
			GridPane roomManagementPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(roomManagementPane);
			roomManagementPane.prefHeightProperty().bind(mainPane.heightProperty());
			roomManagementPane.prefWidthProperty().bind(mainPane.widthProperty());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void usersButtonClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersPane.fxml"));
			GridPane usersPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(usersPane);
			usersPane.prefHeightProperty().bind(mainPane.heightProperty());
			usersPane.prefWidthProperty().bind(mainPane.widthProperty());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
