package jdbcManager;
import interfaces.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import model.*;


public class JDBCPatientController implements PatientInterface{
	private static JDBCPatientController singleton;
	
	public static JDBCPatientController getPatientController () {
		if (singleton == null) {
			singleton = new JDBCPatientController ();
		}
		return singleton;
	}
	
	public boolean insertCompletePatient (Patient patient) throws Exception {
		String sql = "INSERT INTO patient (name, gender,diagnose, dob, dateAdmission)" 
					+"VALUES(?,?,?,?,?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
	
		prep.setString(1, patient.getName());
		prep.setString(2,  patient.getGender().name().toLowerCase());
		prep.setString(3, patient.getDiagnose());
		prep.setDate(4, patient.getDob());
		prep.setDate(5,  patient.getDateAdmission());
		prep.executeUpdate();
		prep.close();
		return true;
	}
	
	public boolean insertNoDiagnosePatient (Patient patient) throws Exception {
		String sql = "INSERT INTO patient (name, gender, dob, dateAdmission)" 
					+"VALUES(?,?,?,?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
	
		prep.setString(1, patient.getName());
		prep.setString(2,  patient.getGender().name().toLowerCase());
		prep.setDate(3, patient.getDob());
		prep.setDate(4,  patient.getDateAdmission());
		prep.executeUpdate();
		prep.close();
		return true;
	}
	
	

	public void addNurseToPatient (Nurse nurse, Patient patient)throws Exception{
		String sql = "INSERT INTO nurse_patient"
				+ "(nurse_id, patient_id) VALUES (?, ?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  nurse.getId());
		prep.setInt(2,  patient.getId());
		prep.executeUpdate();
		prep.close();
		
	}
	//Finish tomorrow
	public void addTreatmentToPatient (Treatment treatment) throws Exception{
		String sql = "INSERT INTO treatment ( )"
		
	}
	
		
	
	
	public boolean deletePatient (Patient patient) throws Exception {
		String sql = "DELETE FROM patient WHERE id = ?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,patient.getId());
		prep.executeUpdate();
		return true;
	}
	
	
	public List<Patient> getAllPatients () throws Exception {
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM patient";
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> patientList= new LinkedList <Patient>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			Patient.sex gender = Patient.sex.valueOf(rs.getString("gender").toUpperCase());
			String diagnose = rs.getString("diagnose");
			Date dob = rs.getDate("dob");
			Date dateAdmission = rs.getDate("dateAdmission");
			Patient searchPatient = new Patient (Id, name, gender, diagnose, dob, dateAdmission);
			patientList.add(searchPatient);
		}
		stmt.close();
		return patientList;
	}
	
	public Patient  getpatientWithoutTreatmentsAndBills (Integer id) throws Exception{
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT id, name, gender, dob, dateAdmission, room_id FROM patient";
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> patientList= new LinkedList <Patient>();
		rs.next();
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			Patient.sex gender = Patient.sex.valueOf(rs.getString("gender").toUpperCase());
			Date dob = rs.getDate("dob");
			Date dateAdmission = rs.getDate("dateAdmission");
			Integer room_id = rs.getInt("room_id");
			Room room= JDBCRoomController.getRoomController().searchRoomById(room_id);
			Patient searchPatient = new Patient (Id, name, gender, dob, dateAdmission, room);
			
		
		stmt.close();
		return searchPatient;
		
	}
	
	public List<Bills> getBillsFromPatient (Patient patient) throws Exception{
		String sql= "SELECT * FROM bills WHERE patient_id = ? ";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, patient.getId());
		ResultSet rs = prep.executeQuery();
		List<Bills> billsList = new LinkedList<Bills>();
		while(rs.next()) {
			int id =rs.getInt("id");
			float totalCost = rs.getFloat("totalcost");
			String bankId =rs.getString("bankId");
			boolean paid = rs.getBoolean("paid");
			Bills searchBill = new Bills (id, totalCost,bankId, paid);
			billsList.add(searchBill);
					
		}
		return billsList;
		
	}
	
	public List<Treatment> getTreatmentsFromPatient (Patient patient) throws Exception{
		String sql = "SELECT * FROM treatment WHERE patient_id =?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, patient.getId());
		ResultSet rs= prep.executeQuery();
		List<Treatment> treatmentsList= new LinkedList<Treatment>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String routeOfAdmin = rs.getString("routeOfAdmin");
			Date startDate =rs.getDate("startDate");
			Date endDate =rs.getDate("endDate");
			Float cost =rs.getFloat("cost");
			String treatmentType =rs.getString("treatmentType");
			String dose =rs.getString("dose");
			Integer prescriber_id= ((Treatment) rs).getPrescriber().getId();
			Doctor prescriber = JDBCDoctorController.getDoctorController().searchDoctorById(prescriber_id);
			Treatment searchTreatment = new Treatment (id,routeOfAdmin, startDate, endDate,
					cost, treatmentType, dose, prescriber);
			treatmentsList.add(searchTreatment);
		}
		return treatmentsList;
	}
	
	public Patient searchPatientById (Integer id) throws Exception {
		String sql = "SELECT * FROM patient WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
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
	
	public void updatePatient (Patient patient) throws Exception{
		String sql = "UPDATE patient SET name=?, gender=?, diagnose=?, dob=?, date_of_admission=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, patient.getName());
		prep.setString(2, patient.getGender().name().toLowerCase());
		prep.setString(3, patient.getDiagnose());
		prep.setDate(4, patient.getDob());
		prep.setDate(5, patient.getDateAdmission());
		prep.setInt(6, patient.getId());
		prep.executeUpdate();
	}

}
