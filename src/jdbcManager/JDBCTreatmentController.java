package jdbcManager;
import interfaces.*;
import java.sql.*;

import model.Doctor;
import model.Treatment;

public class JDBCTreatmentController implements TreatmentInterface {
  
	private static JDBCTreatmentController singleton;
	public JDBCTreatmentController getTreatmentController() {
		if (singleton == null) {
			singleton = new JDBCTreatmentController (); 
			
		}
		return singleton;
		
	}
	
	public boolean insertTreatment (Treatment treatment) throws Exception {
		String sql = "INSERT INTO treatment ( routeOfAdmin, startDate, endDate, cost, type, dose, doctor_id)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		
		prep.setString(1,treatment.getRouteOfAdmin());
		prep.setDate(2, treatment.getStartDate());
		prep.setDate(3, treatment.getEndDate());
		prep.setFloat(4, treatment.getCost());
		prep.setString(5, treatment.getTreatmentType());
		prep.setString(6, treatment.getDose());
		prep.setInt(7, treatment.getPrescriber().getId());
		prep.executeUpdate();
		prep.close();
		return true;
	}
	
	public boolean deleteTreatment (Treatment treatment) throws Exception {
		String sql = "DELETE FROM treatment WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1,  treatment.getId());
		prep.executeUpdate();
		return true;
	}
	
	public Treatment searchTreatmentById (Integer id) throws Exception {
		String sql = "SELECT * FROM treatment WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
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
		Treatment treatment = new Treatment (Id, route, startDate, endDate, cost, type, dose, prescriber);
		return treatment;
	}
	
	public Treatment updateTreatment (Treatment treatment) throws Exception {
		String sql = "UPDATE treatment SET routeOfAdmin=?, startDate=?, endDate=?, cost=?, type=?, dose=?, doctor_id=? WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, treatment.getRouteOfAdmin());
		prep.setDate(2, treatment.getStartDate());
		prep.setDate(3, treatment.getEndDate());
		prep.setFloat(4, treatment.getCost());
		prep.setString(5, treatment.getTreatmentType());
		prep.setString(6, treatment.getDose());
		prep.setInt(7, treatment.getPrescriber().getId());
		prep.setInt(8, treatment.getId());
		prep.executeUpdate();
		return treatment;	
	}
	
}
