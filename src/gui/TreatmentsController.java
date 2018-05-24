package gui;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import interfaces.NurseInterface;
import interfaces.PatientInterface;
import interfaces.TreatmentInterface;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdbcManager.JDBCNurseController;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCTreatmentController;
import model.Treatment;
import model.Bills;
import model.Doctor;
import model.Patient;

public class TreatmentsController implements Initializable{
	
	private Pane mainPane;
	private Patient patient;
	private Doctor doctor; // Only doctors are allowed to add and delete treatments from a patient
	private PatientDetailsController.paneType permission;

    @FXML
    private GridPane treatmentsPane;

    @FXML
    private TableView<Treatment> treatmentsTable;

    @FXML
    private TableColumn<Treatment, String> typeColumn;

    @FXML
    private TableColumn<Treatment, Date> startColumn;

    @FXML
    private TableColumn<Treatment, Date> endColumn;

    @FXML
    private TableColumn<Treatment, String> doseColumn;

    @FXML
    private TableColumn<Treatment, Float> costColumn;
    
    @FXML
    private TableColumn<Treatment, String> routeAdminColumn;
    
    @FXML
    private TextField typeTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField doseTextField;

    @FXML
    private TextField costTextField;
    
    @FXML
    private TextField adminRouteTextField;


    @FXML
    private Button okButton;

    @FXML
    private Button addNewButton;
    
    @FXML
    private Button deleteButton;

    @FXML
    void addNewClicked(ActionEvent event) {
    	Alert a= new Alert(Alert.AlertType.ERROR);
    	String type=typeTextField.getText();
    	if(type.trim().equals("")) {
    		a.setHeaderText("No type");
    		a.setContentText("No treatment without a type allowed");
    		a.showAndWait();
    	}
    	else {
    		LocalDate startLDate=startDatePicker.getValue();
    		if(startLDate!=null) {
    			LocalDate endLDate=endDatePicker.getValue();
    			if(endLDate!=null) {
    				String dose=doseTextField.getText();
    				String routeOfAdmin= adminRouteTextField.getText();
    				String costRead=costTextField.getText();
    				if(costRead.trim().equals("")) {
    					a.setHeaderText("No cost");
    		    		a.setContentText("No treatment without a cost allowed.\n Free treatments have cost=0");
    		    		a.showAndWait();
    				}
    				try {
    					Float cost= Float.parseFloat(costRead);
    					Treatment treatment;
    					if(cost!=0) {
    						TextInputDialog billingDialog = new TextInputDialog("");
    						billingDialog.setTitle("Billing information");
							billingDialog.setHeaderText("Enter the billing information");
							billingDialog.setContentText("BankID:");
							Optional<String> billingID = billingDialog.showAndWait();
							Bills bill= new Bills(cost,billingID.get(),false,patient);
							treatment= new Treatment(routeOfAdmin,Date.valueOf(startLDate),Date.valueOf(endLDate),cost,type,
									dose,doctor,patient,bill);
							TreatmentInterface treatmentController= JDBCTreatmentController.getTreatmentController();
	    					treatmentController.insertTreatment(treatment);
    					}
    					else {
    					treatment= new Treatment(routeOfAdmin,Date.valueOf(startLDate),Date.valueOf(endLDate),cost,type,
								dose,doctor,patient);
    					
    					TreatmentInterface treatmentController= JDBCTreatmentController.getTreatmentController();
    					treatmentController.insertTreatmentWithoutBill(treatment);
    					}
    					setTreatments();
    				}catch (NumberFormatException ex) {
    					a.setHeaderText("Costs are real numbers");
    		    		a.showAndWait();
    				} catch (Exception e) {
						e.printStackTrace();
					}
    			}
    			else {
    				a.setHeaderText("No end date");
    	    		a.setContentText("No treatment without an end date allowed");
    	    		a.showAndWait();
    			}
    		}
    		else {
    			a.setHeaderText("No start date");
        		a.setContentText("No treatment without a start date allowed");
        		a.showAndWait();
    		}
    	}

    }

    @FXML
    void okButtonClicked(ActionEvent event) {
    	try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("PatientDetails.fxml"));
        GridPane patientDetails=(GridPane) loader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(patientDetails);
        patientDetails.prefHeightProperty().bind(mainPane.heightProperty());
        patientDetails.prefWidthProperty().bind(mainPane.widthProperty());
        
        PatientDetailsController controller= loader.<PatientDetailsController>getController();
        controller.initComponents(patient, mainPane, permission,doctor,null);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    @FXML
    public void deleteButtonClicked(ActionEvent event) {
    	Treatment treatment=treatmentsTable.getSelectionModel().getSelectedItem();
    	if(treatment!=null) {
    	Alert a = new Alert(AlertType.CONFIRMATION, "Do you want to delete this treatment?",
				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
		a.setTitle("Delete");
		a.setHeaderText("Delete treatment");
		String confirmation = a.showAndWait().get().getText();
		if (confirmation.equals("Yes")) {
			try {
				TreatmentInterface treatmentController = JDBCTreatmentController.getTreatmentController();
				treatmentController.deleteTreatment(treatment);
				setTreatments();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	}
    	else {
    		Alert a = new Alert(AlertType.WARNING);
    		a.setHeaderText("No treatment selected");
    		a.showAndWait();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		typeColumn.setCellValueFactory(new PropertyValueFactory<Treatment,String>("treatmentType"));
		startColumn.setCellValueFactory(new PropertyValueFactory<Treatment,Date>("startDate"));
		endColumn.setCellValueFactory(new PropertyValueFactory<Treatment,Date>("endDate"));
		doseColumn.setCellValueFactory(new PropertyValueFactory<Treatment,String>("dose"));
		routeAdminColumn.setCellValueFactory(new PropertyValueFactory<Treatment,String>("routeOfAdmin"));
		costColumn.setCellValueFactory(new PropertyValueFactory<Treatment,Float>("cost"));
	}
	
	public void initComponents(Patient patient, Pane mainPane, Doctor doctor, PatientDetailsController.paneType permission) {
		this.patient=patient;
		this.mainPane=mainPane;
		this.doctor=doctor;
		this.permission=permission;
		if(doctor!=null) {
			typeTextField.setVisible(true);
			adminRouteTextField.setVisible(true);
			costTextField.setVisible(true);
			endDatePicker.setVisible(true);
			startDatePicker.setVisible(true);
			doseTextField.setVisible(true);
		}
		else {
			typeTextField.setVisible(false);
			adminRouteTextField.setVisible(false);
			costTextField.setVisible(false);
			endDatePicker.setVisible(false);
			startDatePicker.setVisible(false);
			doseTextField.setVisible(false);
			addNewButton.setVisible(false);
			deleteButton.setVisible(false);
		}
		
		setTreatments();
	}
	
	private void setTreatments() {
		try {
			PatientInterface controller = JDBCPatientController.getPatientController();
			List<Treatment> treatments = controller.getTreatmentsFromPatient(patient);

			ObservableList<Treatment> treatmentsFromPatient = FXCollections.observableArrayList();
			treatmentsFromPatient.addAll(treatments);
			treatmentsTable.getItems().clear();
			treatmentsTable.getItems().addAll(treatmentsFromPatient);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}

