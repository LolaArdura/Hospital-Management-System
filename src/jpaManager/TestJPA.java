package jpaManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import model.*;
import interfaces.*;

import model.Sex;
import tables.DatabaseTables;

public class TestJPA {
 public static void main (String [] args) throws Exception {
	 
	 
	 //Complete patient with list  of bills and treatments and nurses
	/* Date dob = new Date (1998-02-01);
		Date startDate = new Date (1998-02-01);
		Date endDate = new Date (1998-02-01);
		 Date admission = new Date (2018-04-03);
		 Room room1 = new Room (9, 200, "suite", 2, 3, 15 );
		 JPARoomController.getJPARoomController().insertRoom(room1);
	     Bills b1= new Bills ( 20,"5115651", false,null);
		 JPABillsController.getJPABillsController().insertBills(b1);
		 List <Bills> billsList= new LinkedList <Bills> ();
		 billsList.add(b1);
	   Treatment t = new Treatment ( 20, "oral", startDate, endDate, 20, "extrange", "20 pills", null  , null, b1);
		 JPATreatmentController.getTreatmentController().insertTreatment(t);
		 List <Treatment> tList = new LinkedList <Treatment>();
		 tList.add(t);
		Nurse n = new Nurse (30, "Elena","morning", "inyections");
		JPANurseController.getNurseController().insertNurse(n);
		 List <Nurse> nursesList= new LinkedList <Nurse>();
		 nursesList.add(n);
		
		Patient pcompleto = new Patient(8, "Lola ", Sex.female, "ill", dob, admission, tList, nursesList, billsList, room1);
		JPAPatientController.getPatientController().insertCompletePatient(pcompleto);
		List <Patient> patientsList= JPAPatientController.getPatientController().getPatientWithoutTreatmentsAndBills();
	   System.out.println(patientsList);*/
	
		
		
		
		 //TEST ROOMCONTROLLE METHODS
		 
		// insert Room without list of patient 
	
	 //Comprobacion Update room
	     /* Room room1 = new Room (5, 200, "suite", 2, 3, 15 );
		 Room room2 = new Room (33, 102, "normal", 2, 3, 5);
		 Room room3 = new Room (20, 201, "normal", 2, 3, 5 ); 
         Room room4 = new Room (1, 22, "box",1 ,2 ,8);
        // JPARoomController.getJPARoomController().insertRoom(room4);
		 room4.setNumber(55);
		 JPARoomController.getJPARoomController().updateRoom(room4);
		 List<Room> rList= JPARoomController.getJPARoomController().getAllRooms();
			for (Room r : rList) {
				System.out.println(r);
			}*/
		///JPARoomController.getJPARoomController().insertRoom(room1);
		//JPARoomController.getJPARoomController().insertRoom(room2);
		 //JPARoomController.getJPARoomController().insertRoom(room3);
		/* List<Room> rList= JPARoomController.getJPARoomController().getFreeRooms();
		for (Room r : rList) {
			System.out.println(r);
		}*/
		 
		
		
		 //delete room
		// JPARoomController.getJPARoomController().deleteRoom(room2);
		 
		 //getAllRooms
		 /*List <Room> roomList = JPARoomController.getJPARoomController().getAllRooms();
		 for (Room room: roomList ) {
			 System.out.println(room);
	 	 }/*
		 //searchRoomsByPatient
		 Room r = new Room ();
		 r=  JPARoomController.getJPARoomController().searchRoomById(1);
		 System.out.println(r);
		
		 //update room
		 room2 = new Room (2,133, "suite", 2 , 4, 15);
		 JPARoomController.getJPARoomController().insertRoom(room2);
		 System.out.println(room2);
		 
		//comprobacion getRoomsType
		 List <Room> roomSameType= JPARoomController.getJPARoomController().getRoomsByType("normal");
		 for (Room room: roomSameType ) {
			 System.out.println(room);
	 	 }*/
		 
		 //getRoomsAndCost
		// List <Room> roomList1= JPARoomController.getJPARoomController().getRoomsAndCosts();
		// for (Room room: roomList1 ) {
		//	 System.out.println(room);
	 	// }
	 
	 
	 
	 //DatabaseTables.createTables();
	/* Date dob = Date.valueOf(LocalDate.of(1999,1, 7));
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
	// Patient Lolita = new Patient (1,"Lolita", Sex.female, "mental disorder", dob, dateAdmission);
	// JPAPatientController.getPatientController().insertCompletePatient(Lolita);
	// System.out.println(Lolita);
	// Patient Lolita = new Patient (1,"Lolita", Sex.female, "mental disorder", dob, dateAdmission);
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
	//Nurse nurse1 = new Nurse(1, "Paquita", "morning", "injections" );
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
	// JPANurseController.getNurseController().addPatientToNurse(nurse1, Lolita);
	// System.out.println(JPANurseController.getNurseController().getAllNurses());
	/* JPANurseController.getNurseController().addPatientToNurse(nurse1, Lolita);
	 
	 System.out.println(JPANurseController.getNurseController().getAllNurses());*/
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
 }
}
