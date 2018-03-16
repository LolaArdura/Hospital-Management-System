package jdbcManager;

import java.util.Date;
import java.util.List;

import model.Doctor;
import model.Nurse;
import model.Patient;
import model.Patient.sex;
import tables.DatabaseTables;

public class Test {

 public static void main (String[] args) throws Exception {
	 //create tables
	 //DatabaseTables.createTables(); 

	 Doctor doctor_ = new Doctor(12, "Pepe", null , "morning", "cardiology" );
	 DoctorController.getDoctorController().insertDoctor (doctor_);
	 //insert doctor
<<<<<<< HEAD
	 /*Doctor doctor_ = new Doctor(06, "Pepe", null , "morning", "cardiology" );
	 //DoctorController.getDoctorController().insertDoctor (doctor_);
=======
	 //DoctorController.getDoctorController().insertDoctor (doctor_);
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System.git
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
	 Date dob = new Date();
	 Date dateAdmission = new Date ();
	 Patient patient_ = new Patient(01, "Manola", sex.WOMEN , DEFAULT, dob, dateAdmission );
	 PatientController.getPatientController().insertPatient(patient_);
	 List<Patient> patients = PatientController.getPatientController().getAllPatients();
	 System.out.println(patients);
 }
}
