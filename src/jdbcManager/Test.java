package jdbcManager;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.*;
import tables.DatabaseTables;

public class Test {

 public static void main (String[] args) throws Exception {
	 //create tables
     DatabaseTables.createTables(); 

	 //insert doctor

	//Doctor doctor1 = new Doctor(1, "Pepe", null , "morning", "cardiology" );
	// Doctor doctor2 = new Doctor (2, "Paco", null, "afternoon", "nephrology");
	// JDBCDoctorController.getDoctorController().insertDoctor (doctor1);
	// JDBCDoctorController.getDoctorController().insertDoctor(doctor2);
	 //imprimo doctores
//     List<Doctor> listDoctors = JDBCDoctorController.getDoctorController().getAllDoctors();
//	 System.out.println(listDoctors);
	 //delete and print again the list to confirm !!!! no delete
//	 JDBCDoctorController.getDoctorController().deleteDoctor(doctor1);
//	 listDoctors.clear();
//	 listDoctors.addAll(JDBCDoctorController.getDoctorController().getAllDoctors());
//	 System.out.println(listDoctors);
	 
	 //search
	/* try {
	 Doctor c=JDBCDoctorController.getDoctorController().searchDoctorById(1);
	 System.out.println(c);
	 Doctor d = JDBCDoctorController.getDoctorController().searchDoctorByName("Pepe");
	 System.out.println(d);
	 //search doctor
	 System.out.println(c);
	 }catch(SQLException e) {
		 e.printStackTrace();
	 }catch(Exception e) {
		 System.out.println("No doctor found");
	 }*/
	 
	

	 //insert Nurse
	 /* Nurse nurse1 = new Nurse ("Elena",null,"afternoon", "inyections");
	  Nurse nurse2 = new Nurse ("Paula",null, "morning", "pills administration");
	  JDBCNurseController.getNurseController().deleteNurse();
	  List<Nurse> listaNurses= JDBCNurseController.getNurseController().getAllNurses();
	  System.out.println(listaNurses);*/
	  
	 
	//insert patient
//     Date dob = new Date (2002/03/03);
//     Date dob2 = new Date (1654/06/03);     
//     Date dob3 = new Date (1998/78/03);
//	 Date dateAdmission = new Date (2018/01/02);
//	 Patient patient1 = new Patient( "Ana", Sex.female , dob, dateAdmission );
//	 Patient patient2 = new Patient( "Pepa", Sex.female , dob2, dateAdmission );
//	 Patient patient3 = new Patient( "Manola", Sex.female , dob3, dateAdmission );
//
//	 JDBCPatientController.getPatientController().insertNoDiagnosePatient(patient1);
//	List<Patient> listpatients = JDBCPatientController.getPatientController().getAllPatients();
//	 System.out.println(listpatients);//La fecha es 1970 por razones descconocidas
//	 //search patient
//	 
//	 System.out.println(patient1); //PROBLEMA: al search patient el diagnose sale null
	

//insert bill
	 //Bills bill_ = new Bills (package jdbcManager);
	 
//(float) 200.4," 98512658", true);
	 //BillsController.getBillsController().insertBills(bill_);
	 
	 //insert room
	/* Room room1 = new Room ( 01, 19, roomType.BOX, 5, 2 , 20) ; 
	 Room room3 = new Room (02,20, roomType.DOUBLE, 4, 2,20);
	 Room room4 = new Room (03,22, roomType.DOUBLE, 4, 2,50);
	// JDBCRoomController.getRoomController().insertRoom(room1); 
	// JDBCRoomController.getRoomController().insertRoom(room3);
	// JDBCRoomController.getRoomController().insertRoom(room4);
	 
	 //Print list of Rooms
	// List <Room> listRooms = JDBCRoomController.getRoomController().getAllRooms();
	// System.out.println(listRooms);
	 
	 //Borro una habitacion y comprubeo si se ha borrado de la lista !!!! no DELETE
	 JDBCRoomController.getRoomController().deleteRoom(room3);
	 System.out.println(room3);
	// JDBCRoomController.getRoomController().getAllRooms();
	// System.out.println(listRooms);
	 
	 //Update
	 JDBCRoomController.getRoomController().updateRoom(room4);*/
	 

	 
 }
}
