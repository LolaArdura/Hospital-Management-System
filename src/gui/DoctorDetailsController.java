package gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import jpaManager.JPADoctorController;
import interfaces.DoctorInterface;
import model.Doctor;

public class DoctorDetailsController implements Initializable {

	@FXML
	private GridPane detailsPane;

	@FXML
	private GridPane labelsPane;

	@FXML
	private Button browseButton;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Label nameLabel;

	@FXML
	private Label specialityLabel;

	@FXML
	private Label scheduleLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField specialtyTextField;

	@FXML
	private TextField scheduleTextField;

	@FXML
	private ImageView photo;

	@FXML
	private Label idLab;

	@FXML
	private Label idLabel;

	private Doctor doctor;

	private Pane mainPane;

	private Image defaultImage;

	public void initComponents(Doctor doctor, Pane mainPane) {
		this.doctor = doctor;
		this.mainPane = mainPane;
		idLabel.setText("" + doctor.getId());
		nameTextField.setText(doctor.getName());
		specialtyTextField.setText(doctor.getSpecialty());
		scheduleTextField.setText(doctor.getSchedule());
		if (doctor.getPhoto() != null) {
			photo.setImage(new Image(new ByteArrayInputStream(doctor.getPhoto())));
		} else {
			try {
				defaultImage = new Image("/Doctors-Male-Avatar2.png");
				photo.setImage(defaultImage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void browseButtonClicked(ActionEvent ev) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose an image");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File file = fileChooser.showOpenDialog(detailsPane.getScene().getWindow());
		try {
			if (file != null) {
				photo.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void okButtonClicked(ActionEvent ev) {
		try {
			DoctorInterface doctorController = JPADoctorController.getJPADoctorController();

			doctor.setName(nameTextField.getText());
			doctor.setSpecialty(specialtyTextField.getText());
			doctor.setSchedule(scheduleTextField.getText());
			BufferedImage bufImage = SwingFXUtils.fromFXImage(this.photo.getImage(), null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufImage, "png", baos);
			doctor.setPhoto(baos.toByteArray());

			doctorController.updateDoctor(doctor);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorsViewPane.fxml"));
			GridPane doctorsPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(doctorsPane);
			doctorsPane.prefHeightProperty().bind(mainPane.heightProperty());
			doctorsPane.prefWidthProperty().bind(mainPane.widthProperty());

			DoctorsPaneController controller = loader.<DoctorsPaneController>getController();
			controller.initComponents(mainPane);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void deleteButtonClicked(ActionEvent ev) {
		Alert a = new Alert(AlertType.CONFIRMATION, "Do you want to delete this doctor?",
				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
		a.setTitle("Delete");
		a.setHeaderText("Delete doctor");
		String confirmation = a.showAndWait().get().getText();
		if (confirmation.equals("Yes")) {
			try {
				JPADoctorController.getJPADoctorController().deleteDoctor(doctor);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorsViewPane.fxml"));
				GridPane doctorsPane = (GridPane) loader.load();
				mainPane.getChildren().clear();
				mainPane.getChildren().add(doctorsPane);
				doctorsPane.prefHeightProperty().bind(mainPane.heightProperty());
				doctorsPane.prefWidthProperty().bind(mainPane.widthProperty());

				DoctorsPaneController controller = loader.<DoctorsPaneController>getController();
				controller.initComponents(mainPane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void cancelButtonClicked(ActionEvent ev) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorsViewPane.fxml"));
			GridPane doctorsPane = (GridPane) loader.load();
			mainPane.getChildren().clear();
			mainPane.getChildren().add(doctorsPane);
			doctorsPane.prefHeightProperty().bind(mainPane.heightProperty());
			doctorsPane.prefWidthProperty().bind(mainPane.widthProperty());
			DoctorsPaneController controller = loader.<DoctorsPaneController>getController();
			controller.initComponents(mainPane);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (nameTextField.getText().trim().equals("")) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("No doctor without name admitted");
						a.showAndWait();
						nameTextField.setText(doctor.getName());
						nameTextField.requestFocus();
					}
				}

			}

		});

		scheduleTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (scheduleTextField.getText().trim().equals("")) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("No doctor without schedule admitted");
						a.showAndWait();
						scheduleTextField.setText(doctor.getSchedule());
						scheduleTextField.requestFocus();
					}
				}
			}
		});

		specialtyTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (specialtyTextField.getText().trim().equals("")) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("No doctor witout specialty admitted");
						a.showAndWait();
						specialtyTextField.setText(doctor.getSpecialty());
						specialtyTextField.requestFocus();
					}
				}
			}
		});

	}

}
