package jdbcManager;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import model.*;

public class PatientController implements PatientInterface{
	private static PatientController singleton;
	
	public static PatientController getPatientController () {
		if (singleton == null) {
			singleton = new PatientController ();
		}
		return singleton;
	}
	
	public boolean insertPatient (Patient patient) throws Exception {
		String sql = "INSERT INTO patient (id, name, gender,diagnose, dob, dateAdmission)" 
					+"VALUES(?,?,?,?,?,?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  patient.getId());
		prep.setString(2, patient.getName());
		prep.setString(3,  patient.getGender().name().toLowerCase());
		prep.setString(4, patient.getDiagnose());
		prep.setDate(5, patient.getDob());
		prep.setDate(6,  patient.getDateAdmission());
		prep.executeUpdate();
		prep.close();
		return true;
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
	//}*/
	
	public boolean deletePatient (Patient patient) throws Exception {
		String sql = "DELETE FROM patient WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,patient.getId());
		prep.executeUpdate();
		return true;
	}
	public List<Patient> getAllPatients () throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM patient";
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> patientList= new LinkedList <Patient>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			Patient.sex gender = Patient.sex.valueOf(rs.getString("gender").toUpperCase());
			String diagnose = rs.getString("diagnose");
			Date dob = rs.getDate("dob");
			Date dateAdmission = rs.getDate("date_of_admission");
			Patient searchPatient = new Patient (Id, name, gender, diagnose, dob, dateAdmission);
			patientList.add(searchPatient);
		}
		stmt.close();
		return patientList;
	}
	
	public Patient searchPatientById (Integer id) throws Exception {
		String sql = "SELECT * FROM patient WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int Id = rs.getInt("id");
		String name = rs.getString("name");
		Patient.sex gender = Patient.sex.valueOf(rs.getString("gender").toUpperCase());
		String diagnose = rs.getString("diagnose");
		Date dob = rs.getDate("dob");
		Date dateAdmission = rs.getDate("date_of_admission");
		Patient patient = new Patient (Id, name, gender, diagnose, dob, dateAdmission);
		return patient;
	}
	
	public Patient updatePatient (Patient patient) throws Exception{
		String sql = "UPDATE patient SET name=?, gender=?, diagnose=?, dob=?, date_of_admission=? WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, patient.getName());
		prep.setString(2, patient.getGender().name().toLowerCase());
		prep.setString(3, patient.getDiagnose());
		prep.setDate(4, patient.getDob());
		prep.setDate(5, patient.getDateAdmission());
		prep.setInt(6, patient.getId());
		prep.executeUpdate();
		return patient;
	}

}
