package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import jdbcManager.*;


public class DatabaseTables {

	public static void createTables() {
		try {
			
			Connection c= DBConnection.getConnection();
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened");
			
			Statement s1=c.createStatement();
			String table1= "CREATE TABLE doctor(\r\n" + 
					"id INT PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"photo BLOB, \r\n"+
					"speciality TEXT NOT NULL, \r\n" + 
					"schedule TEXT NOT NULL \r\n" + 
					")";
			s1.executeUpdate(table1);
			s1.close();
			
			Statement s2=c.createStatement();
			String table2= "CREATE TABLE patient(\r\n" + 
					"id INT PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"gender TEXT NOT NULL, \r\n" + 
					"dob DATE NOT NULL, \r\n" + 
					"dateAdmission DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, \r\n" + 
					"diagnose TEXT DEFAULT 'waiting for diagnose',\r\n" + 
					"room_id INT REFERENCES room(id)\r\n" + 
					")";
			s2.executeUpdate(table2);
			s2.close();
			
			Statement s3=c.createStatement();
			String table3= "CREATE TABLE treatment(\r\n" + 
					"id INT PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"name TEXT NOT NULL, \r\n" + 
					"type TEXT NOT NULL,\r\n" + 
					"duration TEXT NOT NULL,\r\n" + 
					"dose TEXT,\r\n" + 
					"way_of_administration TEXT,\r\n" + 
					"cost REAL NOT NULL,\r\n" + 
					"bill_id INT REFERENCES bills(id),\r\n" + 
					"patient_id INT REFERENCES patient(id),\r\n" + 
					"doctor_id INT REFERENCES doctor(id)\r\n" + 
					")";
			s3.executeUpdate(table3);
			s3.close();
			
			Statement s4=c.createStatement();
			String table4= "CREATE TABLE room(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"floor INTEGER NOT NULL DEFAULT 0,\r\n" + 
					"number INTEGER,\r\n" + 
					"type TEXT NOT NULL DEFAULT 'box',\r\n" + 
					"capacity INT NOT NULL CHECK (capacity>0),\r\n" + 
					"cost_per_day REAL\r\n" + 
					")";
			s4.executeUpdate(table4);
			s4.close();
			
			Statement s5=c.createStatement();
			String table5="CREATE TABLE bills(\r\n" + 
					"id INTEGER AUTOINCREMENT,\r\n" + 
					"cost REAL,\r\n" + 
					"billing_adress TEXT NOT NULL,\r\n" + 
					"patient_id INT,\r\n" + 
					"PRIMARY KEY (id),\r\n" + 
					"FOREIGN KEY (patient_id) REFERENCES patient(id)\r\n" + 
					")";
			s5.executeUpdate(table5);
			s5.close();
			
			Statement s6=c.createStatement();
			String table6="CREATE TABLE nurse(\r\n" + 
					"id INT PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"photo BLOB, \r\n" +
					"schedule TEXT NOT NULL,\r\n" + 
					"role TEXT NOT NULL\r\n" + 
					")";
			s6.executeUpdate(table6);
			s6.close();
			
			Statement s7=c.createStatement();
			String table7="CREATE TABLE nurse_patient(\r\n" + 
					"nurse_id INT REFERENCES nurse(id),\r\n" + 
					"patient_id INT REFERENCES patient(id),\r\n" + 
					"PRIMARY KEY(nurse_id, patient_id)\r\n" + 
					")";
			s7.executeUpdate(table7);
			s7.close();
			
			Statement s8=c.createStatement();
			String table8="CREATE TABLE user(\r\n" +
					"id INT PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"password TEXT NOT NULL, \r\n" +
					"type TEXT NOT NULL \r\n" +
					")";
			s8.executeUpdate(table8);
			s8.close();
			
		
		    System.out.println("Database connection closed.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

