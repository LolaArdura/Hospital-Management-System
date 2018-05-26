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
/*	 Patient patient1 = new Patient ( "Ale", Sex.female,"pneumonia",dob,  dateAdmission );

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
*/	 
	 
	 
	 
	 //TEST JPAPATIENTCONROLLER METHODS:

	 //insertCompletePatient:
	 Patient Lolita = new Patient (1,"Lolita", Sex.female, "mental disorder", dob, dateAdmission);
	 //JPAPatientController.getPatientController().insertCompletePatient(Lolita);
	 //System.out.println(Lolita);
/*	
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
 */
/*	//updatePatient
	//Patient ana =new Patient (5, "Ana", Sex.female,"bipolar", dob, dateAdmission);
	//JPAPatientController.getPatientController().insertNoDiagnosePatient(ana);
	//System.out.println(JPAPatientController.getPatientController().getAllPatients());
	Patient tempPatient = JPAPatientController.getPatientController().searchPatientById(5);
	tempPatient =new Patient (5, "Ana", Sex.female,"hypertension", dob, dateAdmission);
	JPAPatientController.getPatientController().updatePatient(tempPatient);
	System.out.println(JPAPatientController.getPatientController().getAllPatients());
*/	
/*
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
	//TEST JPABILLCONTROLLER METHODS:
/*	Bills bill1 = new Bills(1, 1200, "1234bill", true);
	//Bills bill2 = new Bills(2, 300, "1235bill", false);
	//Bills bill3 = new Bills(3, 15000, "1236bill", true);
	
	//insertBills:
	//getAllBills
	JPABillsController.getJPABillsController().insertBills(bill3);
	System.out.println(JPABillsController.getJPABillsController().getAllBills());
	
	//deleteBills:
	JPABillsController.getJPABillsController().deleteBills(bill1);
	System.out.println(JPABillsController.getJPABillsController().getAllBills());

	//searchBillById:
	System.out.println(JPABillsController.getJPABillsController().searchBillsById(1));
	
	//updatebills
		????????????????????????????
*/	
	 
	//TEST JPANURSECONTROLLER METHODS:
	Nurse nurse1 = new Nurse(1, "Paquita", "morning", "injections" );
/*	Nurse nurse2 = new Nurse(2, "Florencia", "evening", "injections" );
	Nurse nurse3 = new Nurse(3, "Maxima", "night", "injections" );
	//insertNurse:
	JPANurseController.getNurseController().insertNurse(nurse1);
	JPANurseController.getNurseController().insertNurse(nurse2);	
	JPANurseController.getNurseController().insertNurse(nurse3);
	
	//getAllNurses:
	System.out.println(JPANurseController.getNurseController().getAllNurses());

	 //searchNurseById
	 System.out.println(JPANurseController.getNurseController().searchNurseById(1));
	 //searchNurseBySchedule:
	 System.out.println(JPANurseController.getNurseController().searchNurseBySchedule("night"));
 
	 //searchNurseByName:
	 System.out.println(JPANurseController.getNurseController().searchNurseByName("Florencia"));
	 
	 //searchNurseByRole:
	 System.out.println(JPANurseController.getNurseController().searchNurseByRole("injections"));
	 
	 //updateNurse 
	  ???????????????????????????????????????
*/ 
	 //addPatientToNurse
	 JPANurseController.getNurseController().addPatientToNurse(nurse1, Lolita);
	 
	 System.out.println(JPANurseController.getNurseController().getAllNurses());
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
 }
}
