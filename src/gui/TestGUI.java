package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCUserController;
import jdbcManager.JDBConnection;
import jpaManager.DBEntityManager;
import model.Patient;
import model.User;
import tables.DatabaseTables;

public class TestGUI extends Application {
	
	public static void main(String[] args) {
		Application.launch(TestGUI.class,args);
	}
     
     public void start (Stage primaryStage) {
    	 primaryStage.setOnCloseRequest(e ->{
    		 e.consume();
    		 close(primaryStage);
    	 });
    	 
    	 try {
    		List<User> users= JDBCUserController.getUserController().getAllUsers();
    		
    		if(users.isEmpty()) {
    			User user= new User("admin","hospitalAdmin1",User.userType.ADMIN);
    			JDBCUserController.getUserController().insertUser(user);
    			
    			Alert a= new Alert(AlertType.INFORMATION);
    			a.setTitle("No users registered");
    			a.setHeaderText("Default admin user created");
    			a.setContentText("Username: admin\nPassword:hospitalAdmin1");
    			a.showAndWait();
    		}
    	 
    		 try {
				Parent root= FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
                Scene scene=new Scene(root, 560, 405);
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.centerOnScreen();
				primaryStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 
    	 }catch(Exception ex) {
    		 try {
    			DatabaseTables.createTables();
    			User user= new User("admin","hospitalAdmin1",User.userType.ADMIN);
    			JDBCUserController.getUserController().insertUser(user);
    			Alert a= new Alert(AlertType.INFORMATION);
    			a.setTitle("No users registered");
    			a.setHeaderText("Default admin user created");
    			a.setContentText("Username: admin\nPassword:hospitalAdmin1");
    			a.showAndWait();
 				Parent root= FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
                Scene scene=new Scene(root, 560, 405);
 				primaryStage.setScene(scene);
 				primaryStage.setResizable(false);
 				primaryStage.centerOnScreen();
 				primaryStage.show();
 				
 			} catch (IOException e) {
 				e.printStackTrace();
 			} catch (Exception e) {
				e.printStackTrace();
			}
    	 }
     }
     
     public void close(Stage primaryStage) {
    	 Alert a= new Alert(AlertType.CONFIRMATION,"Do you want to exit?",
 				new ButtonType("Yes", ButtonBar.ButtonData.YES), ButtonType.NO);
    	a.setTitle("Exit");
    	a.setHeaderText("Exit");
    	String confirmation = a.showAndWait().get().getText();
 		if (confirmation.equals("Yes")) {
 			try {
				JDBConnection.getConnection().close();
				DBEntityManager.getEntityManager().close();
				primaryStage.close();
			} catch (Exception e) {
				 Alert alert= new Alert(AlertType.ERROR);
				 alert.setHeaderText("Could not exit");
				 alert.showAndWait();
			}
 		}
     }
}
