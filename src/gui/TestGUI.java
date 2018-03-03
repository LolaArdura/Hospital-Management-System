package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestGUI extends Application {
	
	public static void main(String[] args) {
		Application.launch(TestGUI.class,args);
	}
     
     public void start (Stage primaryStage) {
    		 try {
				Parent root= FXMLLoader.load(getClass().getResource("AdminMainScene.fxml"));
				primaryStage.setScene(new Scene(root,500,500));
				primaryStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 
    		 
     }
}
