package gui;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCUserController;
import jdbcManager.JDBConnection;
import model.User;
import tables.DatabaseTables;

public class TestGUI extends Application {
	
	public static void main(String[] args) {
		Application.launch(TestGUI.class,args);
	}
     
     public void start (Stage primaryStage) {
    	 
    	 try {
    		 JDBCPatientController.getPatientController().getAllPatients();
    	 
    		 try {
				Parent root= FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
                Scene scene=new Scene(root, 600, 600);
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    		 
    	 }catch(Exception ex) {
    		 try {
    			User user= new User("admin","hospitalAdmin1",User.userType.ADMIN);
    			JDBCUserController.getUserController().insertUser(user);
    			System.out.println("Admin:"+user);
 				Parent root= FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
                Scene scene=new Scene(root, 600, 600);
 				primaryStage.setScene(scene);
 				primaryStage.show();
 				
 			} catch (IOException e) {
 				e.printStackTrace();
 			} catch (Exception e) {
				e.printStackTrace();
			}
    	 }
     }
}
