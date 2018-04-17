package jdbcManager;



import java.sql.Date;
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
	//DatabaseTables.createTables(); 

	 //insert doctor

	 /*Doctor doctor_ = new Doctor(06, "Pepe", null , "morning", "cardiology" );
	 //DoctorController.getDoctorController().insertDoctor (doctor_);
	List<Doctor> a = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(a);
	 List<Doctor> b = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(b);
	 Doctor c=DoctorController.getDoctorController().searchDoctorById(11);
	 System.out.println(c);
	 
	 //search doctor
	 System.out.println(c);
	 
	 //delete doctor
	 DoctorController.getDoctorController().deleteDoctor(doctor_);
	 //update doctor
	 c.setName("Pepa");
	 c.setSpeciality("neurology");
	 DoctorController.getDoctorController().updateDoctor(c);
	 Doctor d =DoctorController.getDoctorController().searchDoctorById(6);
	 System.out.println(d);*/

	 
	 
	 //insert patient
	 Date dob = new Date(0, 0, 0);
	 Date dateAdmission = new Date (0, 0, 0);
	 Patient patient_ = new Patient( "Manola", sex.WOMEN , dob, dateAdmission );
	 JDBCPatientController.getPatientController().insertNoDiagnosePatient(patient_);
	 List<Patient> patients = JDBCPatientController.getPatientController().getAllPatients();
	 System.out.println(patients);
	 //search patient
	 //PatientController.getPatientController().
	 //System.out.println(patient_); //PROBLEMA: al search patient el diagnose sale null
	 //update doctor 
	 
	 //DBConnection.getConnection().close();//
 

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
	 
	 
 }
}
