package xmlManager;

import java.sql.Date;
import java.util.List;

import jpaManager.JPAPatientController;
import model.Patient;
import model.Sex;

public class xmlTest {
	
	public static void main(String[] args) throws Exception{
		Date dob = new Date (2002/3/03);
		Date dateAdmission = new Date (2018/01/02);
		Patient p1 = new Patient("Ale", Sex.MALE, dob, dateAdmission);
		JPAPatientController.getPatientController().insertNoDiagnosePatient(p1);
		//List<Patient> pt = JPAPatientController.getPatientController().getAllPatients();
		xmlPatient.marshal();
	}
}
