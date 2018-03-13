package jdbcManager;
import java.util.List;

import model.Doctor;
import tables.DatabaseTables;

public class Test {
 public static void main (String[] args) throws Exception {
	  //create tables
	 //DatabaseTables.createTables(); 
	
	 //create a doctor
	 Doctor doctor_ = new Doctor(06, "Pepe", null , "morning", "cardiology" );
	 DoctorController.getDoctorController().insertDoctor (doctor_);
	 List<Doctor> a = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(a);
	 List<Doctor> b = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(b);
	Doctor c=DoctorController.getDoctorController().searchDoctorById(6);
	System.out.println(c);
	DoctorController.getDoctorController().deleteDoctor(doctor_);
	 
	 
 }
}
