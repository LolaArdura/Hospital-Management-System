package jpaManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.*;
import interfaces.*;

import model.Sex;
import tables.DatabaseTables;

public class TestJPA {
 public static void main (String [] args) throws Exception {
	 //DatabaseTables.createTables();
	 Date dob = Date.valueOf(LocalDate.of(1999,1, 7));
	 Date dateAdmission = new Date (24/04/2018) ;
	 Patient patient1 = new Patient (2, "Ale", Sex.female,"pneumonia",dob,  dateAdmission );
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
	Patient patient2 = new Patient (10, "Maria", Sex.FEMALE,"pneumonia",dob,  dateAdmission );
	//JPAPatientController.getPatientController().insertCompletePatient(patient2);
	 Treatment w = new Treatment(7, "intravenous", dob, dateAdmission, 289, "injections", "33", d, patient2, b);
	 //JPATreatmentController.getTreatmentController().insertTreatment(w);
	 //System.out.println(w);
	 List<Treatment> q = JPAPatientController.getPatientController().getTreatmentsFromPatient(patient2);
	 for(Treatment a:q) {
		 System.out.println(a);
	 }
 }
}
