package jdbcManager;
import java.util.List;

import model.Doctor;
import model.Nurse;

public class Test {
 public static void main (String[] args) throws Exception {
	 //create tables
	 //DatabaseTables.createTables(); 
	
	 Doctor doctor_ = new Doctor(12, "Pepe", null , "morning", "cardiology" );
	 DoctorController.getDoctorController().insertDoctor (doctor_);
	 List<Doctor> a = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(a);
	 List<Doctor> b = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(b);
	 Doctor c=DoctorController.getDoctorController().searchDoctorById(11);
	 System.out.println(c);
 }
}
