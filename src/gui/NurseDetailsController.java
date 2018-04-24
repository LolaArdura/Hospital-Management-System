package gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import interfaces.NurseInterface;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import jdbcManager.JDBCNurseController;
import model.Nurse;

public class NurseDetailsController implements Initializable{
	private Pane mainPane;

    @FXML
    private GridPane detailsPane;

    @FXML
    private ImageView photo;

    @FXML
    private Button browseButton;

    @FXML
    private GridPane labelsPane;

    @FXML
    private Label scheduleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField scheduleTextField;

    @FXML
    private TextField roleTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;
    
    @FXML
    private Label idLab;
    
    @FXML
    private Label idLabel;
    
    private Image defaultImage;
    
    private Nurse nurse;
    
    public void initComponents(Nurse nurse,Pane mainPane) {
    	this.nurse=nurse;
    	this.mainPane=mainPane;
    	idLabel.setText(""+nurse.getId());
    	nameTextField.setText(nurse.getName());
    	roleTextField.setText(nurse.getRole());
    	scheduleTextField.setText(nurse.getSchedule());
    	if(nurse.getPhoto()!=null) {
    	photo.setImage(new Image(new ByteArrayInputStream(nurse.getPhoto())));
    	}
    	else {
    	   try {
    		defaultImage=new Image("/Nurse.png");
			photo.setImage(defaultImage);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	}
    }

    @FXML
    void browseButtonClicked(ActionEvent event) {
       FileChooser fileChooser= new FileChooser();
  	   fileChooser.setTitle("Choose an image");
  	   fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.jpg"));
  	   File file=fileChooser.showOpenDialog(detailsPane.getScene().getWindow());
  	   try {
  		 if(file!=null) {
  	     photo.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
  		 }
  	   }catch(Exception ex) {
  		   ex.printStackTrace();
  	   }

    }

    @FXML
    public void cancelButtonClicked(ActionEvent event) {
    	changePane();
    }

    @FXML
   public void okButtonClicked(ActionEvent event) {


    	try {
           NurseInterface nurseController= JDBCNurseController.getNurseController();
           nurse.setName(nameTextField.getText());
           nurse.setRole(roleTextField.getText());
           nurse.setSchedule(scheduleTextField.getText());
           BufferedImage bufImage= SwingFXUtils.fromFXImage(this.photo.getImage(), null);
           ByteArrayOutputStream baos=new ByteArrayOutputStream();
   	       ImageIO.write(bufImage, "png" , baos);
   	       nurse.setPhoto(baos.toByteArray());
   	       nurseController.updateNurse(nurse);
   	       
   	       changePane();
    	}catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    public void deleteButtonClicked(ActionEvent event) {
    	Alert a=new Alert(AlertType.CONFIRMATION,"Do you want to delete this nurse?",
    			new ButtonType("Yes",ButtonBar.ButtonData.YES),ButtonType.NO);
    	a.setTitle("Delete");
    	a.setHeaderText("Delete doctor");
    	String confirmation=a.showAndWait().get().getText();
    	if(confirmation.equals("Yes")) {
    		try {
    		NurseInterface nurseController=JDBCNurseController.getNurseController();
    		nurseController.deleteNurse(nurse);
    		changePane();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private void changePane() {
    	try {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("NursesPane.fxml"));
   	    GridPane nursesPane= (GridPane) loader.load();
   	    mainPane.getChildren().clear();
   	    mainPane.getChildren().add(nursesPane);
   	    nursesPane.prefHeightProperty().bind(mainPane.heightProperty());
   	    nursesPane.prefWidthProperty().bind(mainPane.widthProperty());
   	    
   	    NursesViewPaneController controller=loader.<NursesViewPaneController>getController();
   	    controller.initComponents(mainPane,true);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					if(nameTextField.getText().trim().equals("")) {
						Alert a=new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("No nurse without name admitted");
						a.showAndWait();
						nameTextField.setText(nurse.getName());
						nameTextField.requestFocus();
					}
				}
				
			}
		
	});
		
      scheduleTextField.focusedProperty().addListener(new ChangeListener<Boolean>(){
			
			@Override
			public void changed(ObservableValue<?extends Boolean>arg0, Boolean oldValue,Boolean newValue) {
				if(!newValue) {
					if(scheduleTextField.getText().trim().equals("")) {
						Alert a= new Alert(AlertType.ERROR);
						a.setTitle("ERROR");
						a.setContentText("No nurse without schedule admitted");
						a.showAndWait();
						scheduleTextField.setText(nurse.getSchedule());
						scheduleTextField.requestFocus();
					}
				}
			}
		});
      
      roleTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
    	  
    	  public void changed(ObservableValue<?extends Boolean>arg0, Boolean oldValue, Boolean newValue) {
    		  if(!newValue) {
    			  if(roleTextField.getText().trim().equals("")) {
    				  Alert a= new Alert(AlertType.ERROR);
    				  a.setTitle("ERROR");
    				  a.setContentText("No nurse without role admitted");
    				  a.showAndWait();
    				  roleTextField.setText(nurse.getRole());
    				  roleTextField.requestFocus();
    			  }
    		  }
    	  }
      });
}

}


