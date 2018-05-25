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
		String sql = "INSERT INTO patient (name, gender,diagnose, dob, dateAdmission,room_id)" 
					+"VALUES(?,?,?,?,?,?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setString(1, patient.getName());
		prep.setString(2,  patient.getGender().name().toLowerCase());
		prep.setString(3, patient.getDiagnose());
		prep.setDate(4, patient.getDob());
		prep.setDate(5,  patient.getDateAdmission());
		prep.setInt(6, patient.getRoom().getId());
		prep.executeUpdate();
		prep.close();
		return true;
		
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	public boolean insertNoDiagnosePatient (Patient patient) throws Exception {
		String sql = "INSERT INTO patient (name, gender, dob, dateAdmission)" 
					+"VALUES(?,?,?,?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setString(1, patient.getName());
		prep.setString(2,  patient.getGender().name().toLowerCase());
		prep.setDate(3, patient.getDob());
		prep.setDate(4,  patient.getDateAdmission());
		prep.executeUpdate();
		prep.close();
		return true;
		
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	

	public void addNurseToPatient (Nurse nurse, Patient patient)throws Exception{
		String sql = "INSERT INTO nurse_patient"
				+ "(nurse_id, patient_id) VALUES (?, ?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		try {
		prep.setInt(1,  nurse.getId());
		prep.setInt(2,  patient.getId());
		prep.executeUpdate();
		prep.close();
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	
	public boolean deletePatient (Patient patient) throws Exception {
		String sql = "DELETE FROM patient WHERE id = ?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setInt(1,patient.getId());
		prep.executeUpdate();
		prep.close();
		return true;
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	public void deleteNurseFromPatient (Nurse nurse, Patient patient) throws Exception{
		String sql = "DELETE FROM nurse_patient WHERE patient_id =? and nurse_id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setInt(1, patient.getId());
		prep.setInt(2, nurse.getId());
		prep.executeUpdate();
		prep.close();
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	public List<Patient> getAllPatients () throws Exception {
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM patient";
		
		try {
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> patientList= new LinkedList <Patient>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			Sex gender = Sex.valueOf(rs.getString("gender").toLowerCase());
			String diagnose = rs.getString("diagnose");
			Date dob = rs.getDate("dob");
			Date dateAdmission = rs.getDate("dateAdmission");
			Patient searchPatient = new Patient (Id, name, gender, diagnose, dob, dateAdmission);
			patientList.add(searchPatient);
		}
		rs.close();
		stmt.close();
		return patientList;
		
		}catch (Exception e ) {
			stmt.close();
			throw new Exception();
		}
	}
	
	public List<Patient>  getPatientWithoutTreatmentsAndBills () throws Exception{
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT id, name, gender, dob, dateAdmission, room_id FROM patient";
		
		try {
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> patientList= new LinkedList <Patient>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Sex gender = Sex.valueOf(rs.getString("gender").toLowerCase());
			Date dob = rs.getDate("dob");
			Date dateAdmission = rs.getDate("dateAdmission");
			Integer room_id = rs.getInt("room_id");
			Room room= JDBCRoomController.getRoomController().searchRoomById(room_id);
			Patient searchPatient = new Patient (id, name, gender, dob, dateAdmission, room);
			patientList.add(searchPatient);
	}
		rs.close();
		stmt.close();
		return patientList;
		
		}catch (Exception e ) {
			stmt.close();
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	public List<Bills> getBillsFromPatient (Patient patient) throws Exception{
		String sql= "SELECT * FROM bills WHERE patient_id = ? ";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		sql="SELECT bills.id, bills.cost,bankID,paid FROM bills JOIN treatment ON bills.id=treatment.bill_id "
				+ "WHERE treatment.patient_id=?";
		PreparedStatement p=JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setInt(1, patient.getId());
		List<Bills> billsList = new LinkedList<Bills>();
		ResultSet rs = prep.executeQuery();
		while(rs.next()) {
			int id =rs.getInt("id");
			float totalCost = rs.getFloat("cost");
			String bankId =rs.getString("bankID");
			boolean paid = rs.getBoolean("paid");
			Bills searchBill = new Bills (id, totalCost,bankId, paid);
			billsList.add(searchBill);
					
		}
		rs.close();
		prep.close();
		
		p.setInt(1, patient.getId());
		ResultSet rs2 = p.executeQuery();
		while(rs2.next()) {
			int id =rs2.getInt(1);
			float totalCost = rs2.getFloat(2);
			String bankId =rs2.getString("bankID");
			boolean paid = rs2.getBoolean("paid");
			Bills searchBill = new Bills (id, totalCost,bankId, paid);
			billsList.add(searchBill);		
		}
		rs2.close();
		p.close();
		return billsList;
		}catch (Exception e ) {
			prep.close();
			p.close();
			throw new Exception();
		}
	}
	
	public List<Treatment> getTreatmentsFromPatient (Patient patient) throws Exception{
		String sql = "SELECT * FROM treatment WHERE patient_id =?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setInt(1, patient.getId());
		ResultSet rs= prep.executeQuery();
		List<Treatment> treatmentsList= new LinkedList<Treatment>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String routeOfAdmin = rs.getString("routeOfAdmin");
			Date startDate =rs.getDate("startDate");
			Date endDate =rs.getDate("endDate");
			Float cost =rs.getFloat("cost");
			String treatmentType =rs.getString("type");
			String dose =rs.getString("dose");
			Integer prescriber_id= rs.getInt("doctor_id");
			Doctor prescriber = JDBCDoctorController.getDoctorController().searchDoctorById(prescriber_id);
			Treatment searchTreatment = new Treatment (id,routeOfAdmin, startDate, endDate,
					cost, treatmentType, dose, prescriber);
			searchTreatment.setPatient(patient);
			treatmentsList.add(searchTreatment);
		}
		rs.close();
		prep.close();
		return treatmentsList;
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	public Patient searchPatientById (Integer id) throws Exception {
		String sql = "SELECT * FROM patient WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int Id = rs.getInt("id");
		String name = rs.getString("name");
		Sex gender = Sex.valueOf(rs.getString("gender").toLowerCase());
		String diagnose = rs.getString("diagnose");
		Date dob = rs.getDate("dob");
		Date dateAdmission = rs.getDate("dateAdmission");
		Room room =JDBCRoomController.getRoomController().searchRoomById(rs.getInt("room_id"));
		Patient patient = new Patient (Id, name, gender, diagnose, dob, dateAdmission, room);
		rs.close();
		prep.close();
		return patient;
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}
	
	public void updatePatient (Patient patient) throws Exception{
		String sql = "UPDATE patient SET name=?, gender=?, diagnose=?, dob=?, dateAdmission=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setString(1, patient.getName());
		prep.setString(2, patient.getGender().name().toLowerCase());
		prep.setString(3, patient.getDiagnose());
		prep.setDate(4, patient.getDob());
		prep.setDate(5, patient.getDateAdmission());
		prep.setInt(6, patient.getId());
		prep.executeUpdate();
		prep.close();
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}

	public List<Patient> searchPatientByName(String name) throws Exception {
		String sql = "SELECT * FROM patient WHERE name=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		
		try {
		prep.setString(1, name);
		List<Patient> patients = new LinkedList<Patient>();
		ResultSet rs = prep.executeQuery();
		while(rs.next()) {
		int Id = rs.getInt("id");
		String nameRs = rs.getString("name");
		Sex gender = Sex.valueOf(rs.getString("gender").toLowerCase());
		String diagnose = rs.getString("diagnose");
		Date dob = rs.getDate("dob");
		Date dateAdmission = rs.getDate("dateAdmission");
		Patient patient = new Patient (Id, nameRs, gender, diagnose, dob, dateAdmission);
		patients.add(patient);
		}
		rs.close();
		prep.close();
		return patients;
		}catch (Exception e ) {
			prep.close();
			throw new Exception();
		}
	}

}
