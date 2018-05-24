package gui;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import gui.PatientDetailsController.paneType;
import interfaces.PatientInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdbcManager.JDBCPatientController;
import model.Bills;
import model.Patient;
import model.User;

public class BillsReportController implements Initializable {

	private Patient patient;
	private Pane mainPane;
	private User.userType permission;
	private boolean discharge;

	@FXML
	private GridPane billsPane;

	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button dischargeButton;

	@FXML
	private TableView<Bills> billsTable;

	@FXML
	private TableColumn<Bills, Float> costColumn;

	@FXML
	private TableColumn<Bills, String> bankIdColumn;

	@FXML
	private TableColumn<Bills, Boolean> paidColumn;
	
	@FXML 
	public void okButtonClicked(ActionEvent event) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
		GridPane patientsPane = (GridPane) loader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(patientsPane);

		patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
		patientsPane.prefWidthProperty().bind(mainPane.widthProperty());

		PatientDetailsController paneController = loader.<PatientDetailsController>getController();
		paneController.initComponents(patient, mainPane, paneType.valueOf(permission.name()),null,null);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@FXML
	void dischargeButtonClicked(ActionEvent event) {
		try {
			Alert a = new Alert(AlertType.CONFIRMATION, "Do you want to discharge the patient?",
					new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
			a.setHeaderText("Discharge patient");
			String confirmation = a.showAndWait().get().getText();
			if (confirmation.equals("Yes")) {

				PatientInterface controller = JDBCPatientController.getPatientController();
				controller.deletePatient(patient);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
			GridPane patientsPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(patientsPane);

			patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
			patientsPane.prefWidthProperty().bind(mainPane.widthProperty());

			PatientsViewPaneController paneController = loader.<PatientsViewPaneController>getController();
			paneController.initComponents(mainPane, permission,null,null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public void cancelButtonClicked(ActionEvent event) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsViewPane.fxml"));
		GridPane patientsPane = (GridPane) loader.load();
		mainPane.getChildren().clear();
		mainPane.getChildren().add(patientsPane);

		patientsPane.prefHeightProperty().bind(mainPane.heightProperty());
		patientsPane.prefWidthProperty().bind(mainPane.widthProperty());

		PatientsViewPaneController paneController = loader.<PatientsViewPaneController>getController();
		paneController.initComponents(mainPane, permission,null,null);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initComponents(Patient patient, Pane mainPane, User.userType permission, boolean discharge) {
		this.patient = patient;
		this.mainPane = mainPane;
		this.permission=permission;
		if(discharge) {
			dischargeButton.setVisible(true);
			cancelButton.setVisible(true);
			okButton.setVisible(false);
		}
		else {
			dischargeButton.setVisible(false);
			cancelButton.setVisible(false);
			okButton.setVisible(true);
		}
		try {
			PatientInterface controller = JDBCPatientController.getPatientController();
			List<Bills> bills = controller.getBillsFromPatient(patient);

			ObservableList<Bills> billsFromPatient = FXCollections.observableArrayList();
			billsFromPatient.addAll(bills);
			billsTable.getItems().addAll(billsFromPatient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		costColumn.setCellValueFactory(new PropertyValueFactory<Bills, Float>("totalCost"));
		bankIdColumn.setCellValueFactory(new PropertyValueFactory<Bills, String>("bankID"));
		paidColumn.setCellValueFactory(new PropertyValueFactory<Bills, Boolean>("paid"));
	}

}
