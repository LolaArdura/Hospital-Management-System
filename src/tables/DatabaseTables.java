package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import jdbcManager.*;


public class DatabaseTables {

	public static void createTables() throws Exception {
			Connection c= JDBConnection.getConnection();
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened");
			
			Statement s1=c.createStatement();
			String table1= "CREATE TABLE doctor(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"photo BLOB, \r\n"+
					"specialty TEXT NOT NULL, \r\n" + 
					"schedule TEXT NOT NULL \r\n" + 
					")";
			s1.executeUpdate(table1);
			String sqlSeq1="INSERT INTO sqlite_sequence(name, seq) VALUES ('doctor',1)";
			s1.execute(sqlSeq1);
			s1.close();
			
			Statement s2=c.createStatement();
			String table2= "CREATE TABLE patient(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"gender TEXT NOT NULL, \r\n" + 
					"dob DATE NOT NULL, \r\n" + 
					"dateAdmission DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, \r\n" + 
					"diagnose TEXT DEFAULT 'waiting for diagnose',\r\n" + 
					"room_id INT REFERENCES room(id) ON DELETE RESTRICT ON UPDATE CASCADE\r\n" + 
					")";
			s2.executeUpdate(table2);
			s2.close();
			Statement stmtSeq = c.createStatement();
			String sqlSeq2 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('patient', 1)";
			stmtSeq.executeUpdate(sqlSeq2);
			
			Statement s3=c.createStatement();
			String table3= "CREATE TABLE treatment(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"type TEXT NOT NULL,\r\n" + 
					"startDate DATE NOT NULL,\r\n" + 
					"endDate DATE NOT NULL, \r\n" +
					"routeOfAdmin TEXT, \r\n" +
					"dose TEXT,\r\n" + 
					"cost REAL NOT NULL,\r\n" + 
					"bill_id INT REFERENCES bills(id) ON UPDATE CASCADE ON DELETE SET NULL,\r\n" + 
					"patient_id INT REFERENCES patient(id) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
					"doctor_id INT REFERENCES doctor(id) ON DELETE SET NULL ON UPDATE CASCADE\r\n" + 
					")";
			s3.executeUpdate(table3);
			String sqlSeq3="INSERT INTO sqlite_sequence(name, seq) VALUES ('treatment',1)";
			s1.execute(sqlSeq3);
			s3.close();
			
			Statement s4=c.createStatement();
			String table4= "CREATE TABLE room(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"floor INTEGER NOT NULL DEFAULT 0,\r\n" + 
					"number INTEGER UNIQUE,\r\n" + 
					"type TEXT NOT NULL DEFAULT 'box',\r\n" + 
					"capacity INT NOT NULL CHECK (capacity>0),\r\n" + 
					"costPerDay REAL\r\n" + 
					")";
			s4.executeUpdate(table4);
			String sqlSeq4="INSERT INTO sqlite_sequence(name, seq) VALUES ('room',1)";
			s1.execute(sqlSeq4);
			s4.close();
			
			Statement s5=c.createStatement();
			String table5="CREATE TABLE bills(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"cost REAL,\r\n" + 
					"bankID TEXT NOT NULL,\r\n" + 
					"paid boolean NOT NULL DEFAULT false,\r\n"+
					"patient_id INTEGER REFERENCES patient(id) ON UPDATE CASCADE ON DELETE CASCADE\r\n" + 
					")";
			s5.executeUpdate(table5);
			String sqlSeq5="INSERT INTO sqlite_sequence(name, seq) VALUES ('bills',1)";
			s1.execute(sqlSeq5);
			s5.close();
			
			Statement s6=c.createStatement();
			String table6="CREATE TABLE nurse(\r\n" + 
					"id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"name TEXT NOT NULL,\r\n" + 
					"photo BLOB, \r\n" +
					"schedule TEXT NOT NULL,\r\n" + 
					"role TEXT NOT NULL\r\n" + 
					")";
			s6.executeUpdate(table6);
			String sqlSeq6="INSERT INTO sqlite_sequence(name, seq) VALUES ('nurse',1)";
			s1.execute(sqlSeq6);
			s6.close();
			
			Statement s7=c.createStatement();
			String table7="CREATE TABLE nurse_patient(\r\n" + 
					"nurse_id INT REFERENCES nurse(id) ON UPDATE CASCADE ON DELETE CASCADE,\r\n" + 
					"patient_id INT REFERENCES patient(id) ON UPDATE CASCADE ON DELETE CASCADE,\r\n" + 
					"PRIMARY KEY(nurse_id, patient_id)\r\n" + 
					")";
			s7.executeUpdate(table7);
			String sqlSeq7="INSERT INTO sqlite_sequence(name, seq) VALUES ('nurse_patient',1)";
			s1.execute(sqlSeq7);
			s7.close();
			
			Statement s8=c.createStatement();
			String table8="CREATE TABLE user(\r\n" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, \r\n" + 
					"username STRING NOT NULL UNIQUE,"+
					"password TEXT NOT NULL, \r\n" +
					"type TEXT NOT NULL \r\n" +
					")";
			s8.executeUpdate(table8);
			String sqlSeq8="INSERT INTO sqlite_sequence(name, seq) VALUES ('user',1)";
			s1.execute(sqlSeq8);
			s8.close();
			
		
		
	}

}

