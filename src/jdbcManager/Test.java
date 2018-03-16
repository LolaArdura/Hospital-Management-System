package jdbcManager;
<<<<<<< HEAD
=======
import java.util.List;

import model.Doctor;
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System
import tables.DatabaseTables;

public class Test {
<<<<<<< HEAD
 public static void main (String[] args) {
	  
	// DatabaseTables.createTables();
	 
	  //create a patient
	 //insert patient
	 
=======
 public static void main (String[] args) throws Exception {
	  //create tables
	 //DatabaseTables.createTables(); 
	
	 //insert doctor
	 Doctor doctor_ = new Doctor(06, "Pepe", null , "morning", "cardiology" );
	 //DoctorController.getDoctorController().insertDoctor (doctor_);
	 List<Doctor> a = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(a);
	 List<Doctor> b = DoctorController.getDoctorController().getAllDoctors();
	 System.out.println(b);
	 //search doctor
	 Doctor c=DoctorController.getDoctorController().searchDoctorById(6);
	 System.out.println(c);
	 //delete doctor
	 //DoctorController.getDoctorController().deleteDoctor(doctor_);
	 //update doctor
	 c.setName("Pepa");
	 c.setSpeciality("neurology");
	 DoctorController.getDoctorController().updateDoctor(c);
<<<<<<< HEAD
	 Doctor d =DoctorController.getDoctorController().searchDoctorById(6);
	 System.out.println(d);
	 

	
=======
	 System.out.println(c);
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System.git
	 
 }
}
