package jdbcManager;
import interfaces.*;
import java.sql.*;

import model.Bills;
import model.Doctor;
import model.Patient;
import model.Treatment;

public class JDBCTreatmentController implements TreatmentInterface {
  
	private static JDBCTreatmentController singleton;
	public static JDBCTreatmentController getTreatmentController() {
		if (singleton == null) {
			singleton = new JDBCTreatmentController (); 
			
		}
		return singleton;
		
	}
	
	public boolean insertTreatment (Treatment treatment) throws Exception {
		Bills b = treatment.getBill();
		JDBCBillsController.getBillsController().insertBills(b);
		String query = "SELECT last_inserted_rowid () AS lastId";
		PreparedStatement p = JDBConnection.getConnection().prepareStatement(query);
		ResultSet rs = p.executeQuery();
		Integer bill_id = rs.getInt("lastId");
		String sql = "INSERT INTO treatment ( routeOfAdmin, startDate, endDate, cost, type, dose, doctor_id, patient_id, bill_id)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1,treatment.getRouteOfAdmin());
		prep.setDate(2, treatment.getStartDate());
		prep.setDate(3, treatment.getEndDate());
		prep.setFloat(4, treatment.getCost());
		prep.setString(5, treatment.getTreatmentType());
		prep.setString(6, treatment.getDose());
		prep.setInt(7, treatment.getPrescriber().getId());
		prep.setInt(8, treatment.getPatient().getId());
		prep.setInt(9, bill_id);
		prep.executeUpdate();
		prep.close();
		return true;
	}
	
	public boolean deleteTreatment (Treatment treatment) throws Exception {
		String sql = "DELETE FROM treatment WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  treatment.getId());
		prep.executeUpdate();
		return true;
	}
	
	public Treatment searchTreatmentById (Integer id) throws Exception {
		String sql = "SELECT * FROM treatment WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int Id = rs.getInt("id");
		String route = rs.getString("routeOfAdmin");
		Date startDate = rs.getDate("startDate");
		Date endDate = rs.getDate("endDate");
		float cost = rs.getFloat("cost");
		String type = rs.getString("type");
		String dose = rs.getString("dose");
		Doctor prescriber = JDBCDoctorController.getDoctorController().searchDoctorById(rs.getInt("doctor_id"));
		Patient patient = JDBCPatientController.getPatientController().searchPatientById(rs.getInt("patient_id"));
		Treatment treatment = new Treatment (Id, route, startDate, endDate, cost, type, dose, prescriber, patient);
		return treatment;
	}
	
	public void updateTreatment (Treatment treatment) throws Exception {
		String sql = "UPDATE treatment SET routeOfAdmin=?, startDate=?, endDate=?, cost=?, type=?, dose=?, doctor_id=? WHERE id = ?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, treatment.getRouteOfAdmin());
		prep.setDate(2, treatment.getStartDate());
		prep.setDate(3, treatment.getEndDate());
		prep.setFloat(4, treatment.getCost());
		prep.setString(5, treatment.getTreatmentType());
		prep.setString(6, treatment.getDose());
		prep.setInt(7, treatment.getPrescriber().getId());
		prep.setInt(8, treatment.getId());
		prep.executeUpdate();
	}
	
}
