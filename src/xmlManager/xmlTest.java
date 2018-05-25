package xmlManager;

import java.sql.Date;

import model.Bills;
import model.Doctor;
import model.Nurse;

import java.util.LinkedList;
import java.util.List;

import jpaManager.JPAPatientController;
import model.Patient;
import model.Room;
import model.Room.roomType;
import model.Sex;
import model.Treatment;

public class xmlTest {
	 
	public static void main(String[] args) throws Exception{
	Date dob = new Date (2002/3/03);
		Date dateAdmission = new Date (2018/01/02);
		Room r = new Room (8, 208, roomType.BOX, 2 , 10, 15);
		LinkedList<Treatment> treatments= new LinkedList<>();
		Doctor doctor= new Doctor(2,"Harry","morning","inmunollogy");
		treatments.add(new Treatment(23,"intravenous",dateAdmission,dateAdmission,200,"chemotherapy","deadly dose",doctor));
		LinkedList<Nurse> nurses= new LinkedList<>();
		nurses.add(new Nurse(1,"Lola",null,"morning","injections"));
		LinkedList<Bills> bills=new LinkedList<>();
		bills.add(new Bills(1,200, "123456ESB",false));
		Patient p1 = new Patient(2,"Ale", Sex.male,"dead", dob, dateAdmission,treatments,nurses,bills,r);
		//JPAPatientController.getPatientController().insertNoDiagnosePatient(p1);
		//List<Patient> pt = JPAPatientController.getPatientController().getAllPatients();
		//xmlPatient.marshal(p1, "./xmls/Sample-Patients.xml");
		Patient p=xmlPatient.unmarshal("./xmls/Sample-Patients.xml");
        System.out.println(p);
     
        //System.out.println(p.getListOfTreatments());
	
		//xmlPatient.xml2Html("./xmls/External-Patient.xml", "./xmls/Patient-Style.xslt", "./xmls/External-Patient.html");

	}
}
