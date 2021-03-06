package gui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import interfaces.RoomInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import jdbcManager.JDBCRoomController;
import model.Room;

public class RoomManagementController implements Initializable {

	@FXML
	private GridPane nursesPane;

	@FXML
	private TextField numberTextField;

	@FXML
	private TextField floorTextField;

	@FXML
	private Button addButton;

	@FXML
	private ComboBox<String> searchBox;

	@FXML
	private TableView<Room> roomsTable;

	@FXML
	private TableColumn<Room, Integer> numberColumn;

	@FXML
	private TableColumn<Room, Integer> floorColumn;

	@FXML
	private TableColumn<Room, String> typeColumn;

	@FXML
	private TableColumn<Room, Integer> capacityColumn;

	@FXML
	private TableColumn<Room, Float> costColumn;

	@FXML
	private ChoiceBox<String> roomTypeBox;

	@FXML
	private Label searchLabel;

	@FXML
	private TextField capacityTextField;

	@FXML
	private TextField costTextField;

	@FXML
	private Button deleteButton;

	@FXML
	void addNewButtonClicked(ActionEvent event) {
		try {
			String reading = numberTextField.getText();
			Alert alert = new Alert(AlertType.ERROR);
			if (reading.trim().equals("")) {
				alert.setTitle("ERROR");
				alert.setHeaderText("No number");
				alert.setContentText("A number needs to be specified for the new room");
				alert.showAndWait();
				numberTextField.requestFocus();
			} else {
				Integer roomNumber = Integer.parseInt(reading);
				reading = floorTextField.getText();
				if (reading.trim().equals("")) {
					alert.setTitle("ERROR");
					alert.setHeaderText("No floor");
					alert.setContentText("A floor needs to be specified for the new room");
					alert.showAndWait();
					floorTextField.requestFocus();
				} else {
					Integer floor = Integer.parseInt(reading);
					reading = capacityTextField.getText();
					if (reading.trim().equals("")) {
						alert.setTitle("ERROR");
						alert.setHeaderText("No capacity");
						alert.setContentText("A capacity needs to be specified for the new room");
						alert.showAndWait();
						capacityTextField.requestFocus();
					} else {
						Integer capacity = Integer.parseInt(reading);
						reading = roomTypeBox.getSelectionModel().getSelectedItem();
						if (reading == null) {
							alert.setTitle("ERROR");
							alert.setHeaderText("No room type");
							alert.setContentText("A room type needs to be specified for the new room");
							alert.showAndWait();
						} else {
							Room room = null;
							TextInputDialog costDialog = new TextInputDialog("");
							if (reading.equals("Suite")) {
								Float cost = JDBCRoomController.getRoomController().searchCost(reading.toLowerCase());
								if (cost < 0) {
									costDialog.setTitle("New cost");
									costDialog.setHeaderText("No cost specified for this type of room");
									costDialog.setContentText("Enter a cost:");
									Optional<String> costTyped = costDialog.showAndWait();
									if (costTyped.isPresent()) {
										try {
											cost = Float.parseFloat(costTyped.get());
											room = new Room(roomNumber, reading.toLowerCase(), floor, capacity, cost);

										} catch (Exception ex) {
											Alert a = new Alert(AlertType.ERROR);
											a.showAndWait();
										}
									}
								} else {
									room = new Room(roomNumber, reading.toLowerCase(), floor, capacity, cost);
								}
							} else {
								if (reading.equals("Double")) {
									Float cost = JDBCRoomController.getRoomController()
											.searchCost(reading.toLowerCase());
									if (cost < 0) {
										costDialog.setTitle("New cost");
										costDialog.setHeaderText("No cost specified for this type of room");
										costDialog.setContentText("Enter a cost:");
										Optional<String> costTyped = costDialog.showAndWait();
										if (costTyped.isPresent()) {
											try {
												cost = Float.parseFloat(costTyped.get());
												room = new Room(roomNumber, reading.toLowerCase(), floor, capacity,
														cost);

											} catch (Exception ex) {
												Alert a = new Alert(AlertType.ERROR);
												a.showAndWait();
											}
										}
									} else {
										room = new Room(roomNumber, reading.toLowerCase(), floor, capacity, cost);
									}
								} else {
									if (reading.equals("Individual")) {
										Float cost = JDBCRoomController.getRoomController()
												.searchCost(reading.toLowerCase());
										if (cost > 0) {
											room = new Room(roomNumber,reading.toLowerCase(), floor, capacity,
													cost);

										} else {
											costDialog.setTitle("New cost");
											costDialog.setHeaderText("No cost specified for this type of room");
											costDialog.setContentText("Enter a cost:");
											Optional<String> costTyped = costDialog.showAndWait();
											if (costTyped.isPresent()) {
												try {
													cost = Float.parseFloat(costTyped.get());
													room = new Room(roomNumber, reading.toLowerCase(), floor,
															capacity, cost);
												} catch (Exception ex) {
													Alert a = new Alert(AlertType.ERROR);
													a.showAndWait();
												}
											}
										}
									} else {
										if (reading.equals("BOX")) {
											Float cost = JDBCRoomController.getRoomController()
													.searchCost(reading.toLowerCase());
											if (cost < 0) {
												costDialog.setTitle("New cost");
												costDialog.setHeaderText("No cost specified for this type of room");
												costDialog.setContentText("Enter a cost:");
												Optional<String> costTyped = costDialog.showAndWait();
												if (costTyped.isPresent()) {
													try {
														cost = Float.parseFloat(costTyped.get());
														room = new Room(roomNumber, reading.toLowerCase(), floor, capacity,
																cost);

													} catch (Exception ex) {
														Alert a = new Alert(AlertType.ERROR);
														a.showAndWait();
													}
												}
											} else {
												room = new Room(roomNumber, reading.toLowerCase(), floor, capacity, cost);
											}
										} else {
											Float cost = JDBCRoomController.getRoomController()
													.searchCost(reading.toLowerCase());
											if (cost < 0) {
												costDialog.setTitle("New cost");
												costDialog.setHeaderText("No cost specified for this type of room");
												costDialog.setContentText("Enter a cost:");
												Optional<String> costTyped = costDialog.showAndWait();
												if (costTyped.isPresent()) {
													try {
														cost = Float.parseFloat(costTyped.get());
														room = new Room(roomNumber, reading.toLowerCase(), floor, capacity,
																cost);
													} catch (Exception ex) {
														Alert a = new Alert(AlertType.ERROR);
														a.showAndWait();
													}
												}
											} else {
												room = new Room(roomNumber, reading.toLowerCase(), floor, capacity, cost);
											}
										}
									}
								}
							}
							if (room != null) {
								JDBCRoomController.getRoomController().insertRoom(room);
								setRooms();
								numberTextField.clear();
								floorTextField.clear();
								capacityTextField.clear();
							}
						}
					}
				}
			}

		} catch (NumberFormatException ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("All the fields must be numbers");
			alert.showAndWait();

		} catch (Exception ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("This room number already exists");
			alert.showAndWait();

		}

	}

	@FXML
	void deleteButtonClicked(ActionEvent event) {
		Room room = roomsTable.getSelectionModel().getSelectedItem();
		if (room != null) {
			try {
				JDBCRoomController.getRoomController().deleteRoom(room);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("ERROR");
			a.setHeaderText("No selection");
			a.setContentText("No selected room to be deleted");
			a.showAndWait();
		}
		setRooms();
	}

	@FXML
	void searchBoxActionPerformed(ActionEvent event) {
		String roomType = searchBox.getSelectionModel().getSelectedItem();
		RoomInterface controller = JDBCRoomController.getRoomController();
		try {
			
				List<Room> rooms = controller.getRoomsByType(roomType.toLowerCase());
				setRooms(rooms);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		numberColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("number"));
		floorColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("floor"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("type"));
		capacityColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
		costColumn.setCellValueFactory(new PropertyValueFactory<Room, Float>("costPerDay"));
		roomTypeBox.getItems().addAll("Double", "Suite", "Individual", "Box", "ICU");
		searchBox.getItems().addAll("Double", "Suite", "Individual", "Box", "ICU");

		setRooms();
	}

	private void setRooms() {
		try {
			ObservableList<Room> rooms = FXCollections.observableArrayList();
			RoomInterface controller = JDBCRoomController.getRoomController();
			rooms.addAll(controller.getAllRooms());
			roomsTable.getItems().clear();
			roomsTable.getItems().addAll(rooms);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setRooms(List<Room> rooms) {
		ObservableList<Room> roomsList = FXCollections.observableArrayList();
		roomsList.addAll(rooms);
		roomsTable.getItems().clear();
		roomsTable.getItems().addAll(roomsList);
	}

}
