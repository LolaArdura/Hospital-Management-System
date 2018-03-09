package jdbcManager;

import java.sql.PreparedStatement;
import java.util.*;

import model.Bills;
import model.Nurse;
import model.Patient;
import model.Treatment;

public class PatientController implements PatientInterface{
	private static PatientController singleton;
	
	public PatientController getPatientController () {
		if (singleton == null) {
			singleton = new PatientController ();
		}
		return singleton;
	}
	
	public boolean insertPatient (Patient patient) throws Exception {
		String sql = "INSERT INTO patient (id, name, gender, medicalCondition, dob, dateAdmission)" 
					+"VALUES(?,?,?,?,?,?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  patient.getId());
		prep.setString(2, patient.getName());
		prep.setString(3,  patient.getGender().name().toLowerCase());
		prep.setString(4, patient.getMedicalCondition());
		prep.setDate(5, patient.getDob());
		prep.setDate(6,  patient.getDateAdmission());
		
		prep.executeUpdate();
		prep.close();
		return true;
	}
	
}
	
	//public boolean assignNurse (Nurse nurse, Patient patient)throws Exception{
		
	
	//c.setAutoCommit(false);
		//String sql = "INSERT INTO nurse_patient"
			//	+ "(nurse_id, patient_id) VALUES (?, ?)";
		/*PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  nurse.getId());
		prep.setInt(2,  patient.getId());
		c.commit();//DUDA
		return true;
	}
	
	//public boolean assignTreatment (LinkedList<Treatment>treatment, Patient patient) throws Exception{
		//patient.setTreatments(treatment);
		
		//return true;
	//}
	//public boolean assignBill (LinkedList<Bills>bill, Patient patient)throws Exception{
		//patient.setBills(bill);
		
		//return true;
	//}
	
	public boolean deletePatient (Patient patient) throws Exception {
		String sql = "DELETE FROM patient WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,patient.getId());
		prep.executeUpdate();
		return true;
	}
	public List searchPatient()
	
	
		

}
*/