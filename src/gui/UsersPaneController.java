package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.UserInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import jdbcManager.JDBCUserController;
import model.User;
import model.User.userType;

public class UsersPaneController implements Initializable {

	@FXML
	private GridPane nursesPane;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Button addButton;

	@FXML
	private ComboBox<String> searchBox;

	@FXML
	private Label searchLabel;

	@FXML
	private TableView<User> usersTable;

	@FXML
	private TableColumn<User, String> usernameColumn;

	@FXML
	private TableColumn<User, String> passwordColumn;

	@FXML
	private TableColumn<User, userType> userTypeColumn;

	@FXML
	private ChoiceBox<String> usertypeBox;

	@FXML
	void addNewButtonClicked(ActionEvent event) {
		String name = usernameTextField.getText();
		Alert alert = new Alert(AlertType.ERROR);
		if (name.trim().equals("")) {
			alert.setTitle("ERROR");
			alert.setHeaderText("No username");
			alert.setContentText("A name needs to be specified for the new user");
			alert.showAndWait();
			usernameTextField.requestFocus();
		} else {
			String password = passwordTextField.getText();
			if (password.trim().equals("")) {
				alert.setTitle("ERROR");
				alert.setHeaderText("No password");
				alert.setContentText("A password needs to be specified for the new user");
			} else {
				String usertype = usertypeBox.getSelectionModel().getSelectedItem();
				if (usertype == null) {
					alert.setTitle("ERROR");
					alert.setHeaderText("No user type");
					alert.setContentText("A user type needs to be specified for the new user");
					alert.showAndWait();
				} else {
					User newUser;
					if (usertype.equals("Admin")) {
						newUser = new User(name, password, userType.ADMIN);
					} else {
						if (usertype.equals("Receptionist")) {
							newUser = new User(name, password, userType.RECEPTIONIST);
						} else {
							if (usertype.equals("Doctor")) {
								newUser = new User(name, password, userType.DOCTOR);
							} else {
								newUser = new User(name, password, userType.NURSE);
							}
						}

					}

					UserInterface controller = JDBCUserController.getUserController();
					try {
						controller.insertUser(newUser);
						setUsers();
					} catch (Exception ex) {
						Alert a = new Alert(AlertType.ERROR);
						a.setHeaderText("Invalid username");
						a.setContentText("This username already exists");
						a.showAndWait();
						usernameTextField.requestFocus();
					}

				}
			}
		}
	}

	@FXML
	public void searchBoxActionPerformed(ActionEvent event) {
		try {
			String selectedOption = searchBox.getSelectionModel().getSelectedItem();
			if (selectedOption.equals("Admin")) {
				List<User> users = JDBCUserController.getUserController().searchUserByType(User.userType.ADMIN);
				setUsers(users);
			} else {
				if (selectedOption.equals("Receptionist")) {
					List<User> users = JDBCUserController.getUserController()
							.searchUserByType(User.userType.RECEPTIONIST);
					setUsers(users);
				} else {
					if (selectedOption.equals("Doctor")) {
						List<User> users = JDBCUserController.getUserController()
								.searchUserByType(User.userType.DOCTOR);
						setUsers(users);
					} else {
						if (selectedOption.equals("Nurse")) {
							List<User> users = JDBCUserController.getUserController()
									.searchUserByType(User.userType.NURSE);
							setUsers(users);
						} else {
							setUsers();
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		userTypeColumn.setCellValueFactory(new PropertyValueFactory<User, userType>("typeOfUser"));
		usertypeBox.getItems().addAll("Admin", "Receptionist", "Doctor", "Nurse");
		searchBox.getItems().addAll("Show all", "Admin", "Receptionist", "Doctor", "Nurse");
		setUsers();
	}

	private void setUsers(List<User> users) {
		usersTable.getItems().clear();
		ObservableList<User> usersList = FXCollections.observableArrayList();
		usersList.addAll(users);
		usersTable.getItems().addAll(usersList);

	}

	private void setUsers() {
		try {
			usersTable.getItems().clear();
			ObservableList<User> usersList = FXCollections.observableArrayList();
			usersList.addAll(JDBCUserController.getUserController().getAllUsers());
			usersTable.getItems().addAll(usersList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
