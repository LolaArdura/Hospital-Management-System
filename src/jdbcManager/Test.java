package jdbcManager;



import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Bills;
import model.Doctor;
import model.Nurse;
import model.Patient;
import model.Patient.sex;
import tables.DatabaseTables;

public class Test {

 public static void main (String[] args) throws Exception {
	 //create tables
    // DatabaseTables.createTables(); 

	 //insert doctor

	 //Doctor doctor1 = new Doctor(06, "Pepe", null , "morning", "cardiology" );
	// Doctor doctor2= new Doctor (07, "Paco", null, "afternoon", "nephrology");
	 //JDBCDoctorController.getDoctorController().insertDoctor (doctor1);
	 //JDBCDoctorController.getDoctorController().insertDoctor(doctor2);
    /*List<Doctor> a = JDBCDoctorController.getDoctorController().getAllDoctors();
	 System.out.println(a);
	// List<Doctor> b = JDBCDoctorController.getDoctorController().getAllDoctors();
	 //System.out.println(b);
	 try {
	 Doctor c=JDBCDoctorController.getDoctorController().searchDoctorById(11);
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
	 
	 /*delete doctor
	 JDBCDoctorController.getDoctorController().deleteDoctor(doctor_);
	 //update doctor
	 c.setName("Pepa");
	 c.setSpeciality("neurology");
	 JDBCDoctorController.getDoctorController().updateDoctor(c);
	 Doctor e =JDBCDoctorController.getDoctorController().searchDoctorById(6);
	 System.out.println(e);*/

	 //insert Nurse
	  Nurse nurse1 = new Nurse ("Elena",null,"afternoon", "inyections");
	  JDBCNurseController.getNurseController().insertNurse(nurse1);
	  List<Nurse> listaNurses= JDBCNurseController.getNurseController().getAllNurses();
	  System.out.println(listaNurses);
	 
	 
	//insert patient
	 //Date dob = new Date(0, 0, 0);
	// Date dateAdmission = new Date (0, 0, 0);
	 //Patient patient_ = new Patient( "Manola", sex.WOMEN , dob, dateAdmission );
	 //JDBCPatientController.getPatientController().insertNoDiagnosePatient(patient_);
	 //List<Patient> patients = JDBCPatientController.getPatientController().getAllPatients();
	 //System.out.println(patients);
	 //search patient
	 //PatientController.getPatientController().
	 //System.out.println(patient_); //PROBLEMA: al search patient el diagnose sale null
	 //update doctor 
	 
	// JDBConnection.getConnection().close();
 

//insert bill
	 //Bills bill_ = new Bills (package jdbcManager);
	 
//(float) 200.4," 98512658", true);
	 //BillsController.getBillsController().insertBills(bill_);
	 
	 //insert room
	/* Room room_ = new Room (03, 19, roomType.BOX, 5, 20 , 20) ; 
	 RoomController.getRoomController().insertRoom(room_); 
	 List <Room> b = RoomController.getRoomController().getAllRooms();
	 System.out.println(b);
	 Room a=RoomController.getRoomController().searchRoomById(03);
	 a.setCapacity(8);
	 RoomController.getRoomController().updateRoom(a);
	 System.out.println(a);
	 RoomController.getRoomController().deleteRoom(a);
	 List <Room> c = RoomController.getRoomController().getAllRooms();
	 System.out.println(c);*/
	 
	 //insert Nurse
	 
	 
	 
	 
 }
}
