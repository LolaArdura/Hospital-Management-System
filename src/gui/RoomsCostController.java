package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.RoomInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jdbcManager.JDBCRoomController;
import jpaManager.JPARoomController;
import model.Room;

public class RoomsCostController implements Initializable {

	@FXML
	private Button saveButton;

	@FXML
	private Label suiteLabel;

	@FXML
	private Label doubleLabel;

	@FXML
	private Label individualLabel;

	@FXML
	private Label boxLabel;

	@FXML
	private Label icuLabel;

	@FXML
	private TextField suiteTextField;

	@FXML
	private TextField doubleTextField;

	@FXML
	private TextField individualTextField;

	@FXML
	private TextField boxTextField;

	@FXML
	private TextField icuTextField;

	@FXML
	void saveButtonClicked(ActionEvent event) {
		String roomCost = suiteTextField.getText();
		RoomInterface controller = JDBCRoomController.getRoomController();
		if (!roomCost.equals("No room of this type yet")) {
			try {
				Float cost = Float.parseFloat(roomCost);
				controller.updateCost(cost, "suite");
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("The cost needs to be a number");
				suiteTextField.requestFocus();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		roomCost = doubleTextField.getText();
		if (!roomCost.equals("No room of this type yet")) {
			try {
				Float cost = Float.parseFloat(roomCost);
				controller.updateCost(cost, "double");
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("The cost needs to be a number");
				suiteTextField.requestFocus();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		roomCost = individualTextField.getText();
		if (!roomCost.equals("No room of this type yet")) {
			try {
				Float cost = Float.parseFloat(roomCost);
				controller.updateCost(cost, "individual");
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("The cost needs to be a number");
				suiteTextField.requestFocus();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		roomCost = boxTextField.getText();
		if (!roomCost.equals("No room of this type yet")) {
			try {
				Float cost = Float.parseFloat(roomCost);
				controller.updateCost(cost, "box");
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("The cost needs to be a number");
				suiteTextField.requestFocus();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		roomCost = icuTextField.getText();
		if (!roomCost.equals("No room of this type yet")) {
			try {
				Float cost = Float.parseFloat(roomCost);
				controller.updateCost(cost,"icu");
			} catch (NumberFormatException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("The cost needs to be a number");
				suiteTextField.requestFocus();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			RoomInterface roomController = JDBCRoomController.getRoomController();
			List<Room> rooms = roomController.getRoomsAndCosts();
			for (Room room : rooms) {
				if (room.getType().equals("suite")) {
					suiteTextField.setText("" + room.getCostPerDay());
				} else {
					if (room.getType().equals("double")) {
						doubleTextField.setText("" + room.getCostPerDay());
					} else {
						if (room.getType().equals("individual")) {
							individualTextField.setText("" + room.getCostPerDay());
						} else {
							if (room.getType().equals("box")) {
								boxTextField.setText("" + room.getCostPerDay());
							} else {
								if (room.getType().equals("icu")) {
									icuTextField.setText("" + room.getCostPerDay());
								}
							}
						}
					}
				}
			}

			if (suiteTextField.getText().equals("")) {
				suiteTextField.setEditable(false);
				suiteTextField.setText("No room of this type yet");
			}
			if (doubleTextField.getText().equals("")) {
				doubleTextField.setEditable(false);
				doubleTextField.setText("No room of this type yet");
			}
			if (individualTextField.getText().equals("")) {
				individualTextField.setEditable(false);
				individualTextField.setText("No room of this type yet");
			}
			if (boxTextField.getText().equals("")) {
				boxTextField.setEditable(false);
				boxTextField.setText("No room of this type yet");
			}
			if (icuTextField.getText().equals("")) {
				icuTextField.setEditable(false);
				icuTextField.setText("No room of this type yet");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
