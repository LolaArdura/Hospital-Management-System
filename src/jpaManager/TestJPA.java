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
	 JPANurseController.getNurseController().addPatientToNurse(nurse, patient1);
	 List<Patient> p = JPANurseController.getNurseController().getPatientsFromNurse(nurse);
	for(Patient pat: p) {
		System.out.println(pat);
	}
	 
 }
}
