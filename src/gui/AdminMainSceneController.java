package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdminMainSceneController {
	private User user;

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
			controller.initComponents(mainPane, User.userType.ADMIN, null, null);

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

	public void initComponents(User user) {
		this.user = user;
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
	 		Stage stage= (Stage) adminPane.getScene().getWindow();
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
