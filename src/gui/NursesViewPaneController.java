package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import jdbcManager.JDBCNurseController;
import model.Nurse;

public class NursesViewPaneController implements Initializable {

	private boolean nurseManagement;

	private Pane mainPane;

	@FXML
	private GridPane nursesPane;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField scheduleTextField;

	@FXML
	private TextField roleTextField;

	@FXML
	private TextField pictureTextField;

	@FXML
	private Button browseButton;

	@FXML
	private ListView<Nurse> nurseList;

	@FXML
	private Button viewDetailsButton;

	@FXML
	private Button addButton;

	@FXML
	private ComboBox<String> searchBox;

	@FXML
	private JFXTextField floatTextField;

	@FXML
	private Button viewPatientsButton;

	public void initComponents(Pane mainPane, Boolean nurseManagement) {
		this.mainPane = mainPane;
		this.nurseManagement = nurseManagement;

		if (nurseManagement) {
			nameTextField.setVisible(true);
			roleTextField.setVisible(true);
			scheduleTextField.setVisible(true);
			pictureTextField.setVisible(true);
			browseButton.setVisible(true);
			addButton.setVisible(true);
			viewDetailsButton.setVisible(true);
			viewPatientsButton.setVisible(false);
		} else {
			nameTextField.setVisible(false);
			roleTextField.setVisible(false);
			scheduleTextField.setVisible(false);
			pictureTextField.setVisible(false);
			browseButton.setVisible(false);
			addButton.setVisible(false);
			viewDetailsButton.setVisible(false);
			viewPatientsButton.setVisible(true);
		}
	}

	@FXML
	void addNewButtonClicked(ActionEvent event) {

		String name = nameTextField.getText();
		Alert alert = new Alert(AlertType.ERROR);
		if (name.trim().equals("")) {
			alert.setTitle("ERROR");
			alert.setHeaderText("No name");
			alert.setContentText("A name needs to be specified for the new nurse");
			alert.showAndWait();
			nameTextField.requestFocus();
		} else {
			String role = roleTextField.getText();
			if (role.trim().equals("")) {
				alert.setTitle("ERROR");
				alert.setHeaderText("No role");
				alert.setContentText("A role needs to be specified for the new nurse");
				alert.showAndWait();
				roleTextField.requestFocus();
			} else {
				String schedule = scheduleTextField.getText();
				if (schedule.trim().equals("")) {
					alert.setTitle("ERROR");
					alert.setHeaderText("No schedule");
					alert.setContentText("Schedule needs to be specified for the new nurse");
					alert.showAndWait();
					scheduleTextField.requestFocus();
				} else {
					try {
						Nurse nurse;
						String path = pictureTextField.getText();
						if (!path.trim().equals("")) {
							Path filePath = Paths.get(path);
							byte[] photo = Files.readAllBytes(filePath);
							nurse = new Nurse(name, photo, schedule, role);
						}

						else {
							nurse = new Nurse(name, schedule, role);
						}

						// We insert into the database the new nurse
						JDBCNurseController.getNurseController().insertNurse(nurse);

						// We show the new nurse
						setNurses();

						// We clear the text fields
						nameTextField.clear();
						scheduleTextField.clear();
						roleTextField.clear();
						pictureTextField.clear();

					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	@FXML
	void browseButtonClicked(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose an image");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File file = fileChooser.showOpenDialog(nursesPane.getScene().getWindow());
		if (file != null) {
			pictureTextField.setText(file.getAbsolutePath());
		}
	}

	@FXML
	void searchBoxAcionPerformed(ActionEvent event) {
		String selection = searchBox.getSelectionModel().getSelectedItem();
		if (selection.equals("Name")) {
			floatTextField.setPromptText("Enter name");
		} else {
			if (selection.equals("Role")) {
				floatTextField.setPromptText("Enter role");
			} else {
				floatTextField.setPromptText("Enter schedule");
			}
		}
	}

	@FXML
	void textFieldKeyTyped(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			String selectedOption = searchBox.getSelectionModel().getSelectedItem();
			if (selectedOption != null) {
				String text = floatTextField.getText();
				if (!text.trim().equals("")) {
					if (selectedOption.equals("Name")) {
						try {
							List<Nurse> nurses = JDBCNurseController.getNurseController().searchNurseByName(text);
							setNurses(nurses);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						if (selectedOption.equals("Role")) {
							try {
								List<Nurse> nurses = JDBCNurseController.getNurseController().searchNurseByRole(text);
								setNurses(nurses);
							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							if (selectedOption.equals("Schedule")) {
								try {
									List<Nurse> doctors = JDBCNurseController.getNurseController()
											.searchNurseBySchedule(text);
									setNurses(doctors);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							} else {
								setNurses();
							}
						}
					}
				}
			} else {
				Alert a= new Alert(Alert.AlertType.WARNING);
				a.setTitle("WARNING");
				a.setHeaderText("No selected search field");
				a.showAndWait();
				setNurses();
			}

		}
	}

	@FXML
	void viewDetailsClicked(ActionEvent event) {
		Nurse nurse = nurseList.getSelectionModel().getSelectedItem();
		if (nurse != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NurseDetailsPane.fxml"));
				GridPane nurseDetails = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(nurseDetails);
				nurseDetails.prefHeightProperty().bind(mainPane.heightProperty());
				nurseDetails.prefWidthProperty().bind(mainPane.widthProperty());
				NurseDetailsController controller = loader.<NurseDetailsController>getController();
				controller.initComponents(nurse, mainPane);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@FXML
	public void viewPatientsClicked(ActionEvent event) {
		Nurse nurse = nurseList.getSelectionModel().getSelectedItem();
		if (nurse != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NursePatientPane.fxml"));
				GridPane nursePatient = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(nursePatient);
				nursePatient.prefHeightProperty().bind(mainPane.heightProperty());
				nursePatient.prefWidthProperty().bind(mainPane.widthProperty());

				NursePatientController controller = loader.<NursePatientController>getController();
				controller.initComponents(mainPane, nurse);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setNurses();
		nurseList.setCellFactory(new Callback<ListView<Nurse>, ListCell<Nurse>>() {

			@Override
			public ListCell<Nurse> call(ListView<Nurse> doc) {
				ListCell<Nurse> cell = new ListCell<Nurse>() {

					@Override
					protected void updateItem(Nurse item, boolean bln) {
						super.updateItem(item, bln);
						if (item != null) {
							setText(item.getName());
						}
					}
				};
				return cell;
			}

		});

		searchBox.getItems().addAll("Name", "Role", "Schedule");

		floatTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (floatTextField.getText().trim().equals("")) {
						setNurses();
					}
				}

			}

		});
	}

	private void setNurses() {
		try {
			ObservableList<Nurse> nurses = FXCollections.observableArrayList();
			nurses.addAll(JDBCNurseController.getNurseController().getAllNurses());
			nurseList.getItems().clear();
			nurseList.setItems(nurses);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setNurses(List<Nurse> nurses) {
		ObservableList<Nurse> nur = FXCollections.observableArrayList();
		nur.addAll(nurses);
		nurseList.getItems().clear();
		nurseList.setItems(nur);
	}

}
