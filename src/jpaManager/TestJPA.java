package jpaManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import model.*;
import model.Sex;


public class TestJPA {
 public static void main (String [] args) throws Exception {
	 //DatabaseTables.createTables();
	 Date dob = Date.valueOf(LocalDate.of(1999,1, 7));
	 Date dateAdmission = new Date (24/04/2018) ;
	 Patient patient1 = new Patient ( "Ale", Sex.female,"pneumonia",dob,  dateAdmission );

	 Nurse nurse = new Nurse (2, "Maruja", null, "morning", "bring lunch");
	 
	 
	 //JPAPatientController.getPatientController().insertCompletePatient(patient1);
	// JPANurseController.getNurseController().insertNurse(nurse);
	 //JPANurseController.getNurseController().addPatientToNurse(nurse, patient1);
	 List<Patient> p = JPANurseController.getNurseController().getPatientsFromNurse(nurse);
	
	for(Patient pat: p) {
		System.out.println(pat);
	}
	Doctor d = new Doctor (8, "Manuel", null, "morning", "cardio");
	//JPADoctorController.getJPADoctorController().insertDoctor(d);
	Bills b = new Bills (1, 200, "76839E2", true);
	 //JPABillsController.getJPABillsController().insertBills(b);
	//Patient patient2 = new Patient (10, "Maria", Sex.female,"pneumonia",dob,  dateAdmission );
	//JPAPatientController.getPatientController().insertCompletePatient(patient2);
	 //Treatment w = new Treatment(7, "intravenous", dob, dateAdmission, 289, "injections", "33", d, patient2, b);
	 //JPATreatmentController.getTreatmentController().insertTreatment(w);
	 //System.out.println(w);
	// List<Treatment> q = JPAPatientController.getPatientController().getTreatmentsFromPatient(patient2);
	// for(Treatment a:q) {
		 //System.out.println(a);
	 //}
	 
	 
	 //TEST JPAPATIENTCONROLLER METHODS:
/*
	 //insertCompletePatient:
	 Patient Lolita = new Patient (1,"Lolita", Sex.female, "mental disorder", dob, dateAdmission);
	 JPAPatientController.getPatientController().insertCompletePatient(Lolita);
	 System.out.println(Lolita);
	
	 //InsertNoDiagnosePatient:
	 Patient Pepa = new Patient (2,  "Pepa", Sex.female, dob, dateAdmission);
	 JPAPatientController.getPatientController().insertNoDiagnosePatient(Pepa);
	 System.out.println(Pepa);
	
	 //deletePatient:
	 //getAllPatients
	 JPAPatientController.getPatientController().deletePatient(Pepa);
	 System.out.println(JPAPatientController.getPatientController().getAllPatients());
	 
	 //searchPatientById:
	 Patient searchedPatient = JPAPatientController.getPatientController().searchPatientById(1);
	 System.out.println(searchedPatient);
 
	//updatePatient
	patient1=new Patient ("Ale", Sex.female,"bipolar", dob, dateAdmission);
	System.out.println(patient1);
	
	//addNurseToPatient
	JPAPatientController.getPatientController().addNurseToPatient(nurse, patient1);
	System.out.println(patient1);
	
	//deleteNurseFromPatient:
	JPAPatientController.getPatientController().deleteNurseFromPatient(nurse, patient1);
	System.out.println(patient1);
	
	//getPatentWithoutTreatmentsAndBills: patient1 no tiene ni treatments ni bill, usamos ese.
	//List<Patient> patients =JPAPatientController.getPatientController().getAllPatients();
	//JPAPatientController.getPatientController().getPatientWithoutTreatmentsAndBills(patients);
	
	//getBillsFromPatient:
	System.out.println(JPAPatientController.getPatientController().getBillsFromPatient(patient1));
	
	//getTreatmentsFromPatient:
	System.out.println(JPAPatientController.getPatientController().getTreatmentsFromPatient(patient1));
	
	//searchPatientByName:
	Patient Ines = new Patient (3,"Ines", Sex.female, "mental disorder", dob, dateAdmission);
	JPAPatientController.getPatientController().insertCompletePatient(Ines);
	System.out.println(Ines);
	System.out.println(JPAPatientController.getPatientController().searchPatientByName("Ines"));
	
	 
 */
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }
}
