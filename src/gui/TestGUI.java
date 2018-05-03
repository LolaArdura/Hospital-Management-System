package gui;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdbcManager.JDBCPatientController;
import model.Patient;
import model.Patient.sex;
import tables.DatabaseTables;

public class TestGUI extends Application {
	
	public static void main(String[] args) {
		Application.launch(TestGUI.class,args);
	}
     
     public void start (Stage primaryStage) {
    		 try {
				Parent root= FXMLLoader.load(getClass().getResource("AdminMainScene.fxml"));
                Scene scene=new Scene(root, 600, 600);
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 
    		 
     }
}
