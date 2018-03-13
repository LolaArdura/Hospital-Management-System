package jdbcManager;

import java.sql.*;

import model.Doctor;
import model.Treatment;

public class TreatmentController {
  
	private static TreatmentController singleton;
	public TreatmentController getTreatmentController() {
		if (singleton == null) {
			singleton = new TreatmentController (); 
			
		}
		return singleton;
		
	}
	
	public boolean insertTreatment (Treatment treatment) throws Exception {
		String sql = "INSERT INTO treatment (id, routeOfAdmin, startDate, endDate, cost, type, dose, prescriber)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, treatment.getId());
		prep.setString(2,treatment.getRouteOfAdmin());
		prep.setDate(3, treatment.getStartDate());
		prep.setDate(4, treatment.getEndDate());
		prep.setFloat(5, treatment.getCost());
		prep.setString(6, treatment.getTreatmentType());
		prep.setString(7, treatment.getDose());
		prep.setString(8, treatment.getPrescriber().getName());
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
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT FROM treatment WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = stmt.executeQuery(sql);
		int Id = rs.getInt("id");
		String route = rs.getString("routeOfAdmin");
		Date startDate = rs.getDate("startDate");
		Date endDate = rs.getDate("endDate");
		float cost = rs.getFloat("cost");
		String type = rs.getString("type");
		String dose = rs.getString("dose");
		Doctor prescriber =;//HOW ??
		Treatment treatment = new Treatment (Id, route, startDate, endDate, cost, type, dose, prescriber);
		stmt.close();
		return treatment;
	}
	
	public Treatment updateTreatment (Treatment treatment) throws Exception {
		String sql = "UPDATE treatment SET routeOfAdmin=?, startDate=?, endDate=?, cost=?, type=?, dose=?, prescriber=? WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, treatment.getRouteOfAdmin());
		prep.setDate(2, treatment.getStartDate());
		prep.setDate(3, treatment.getEndDate());
		prep.setFloat(4, treatment.getCost());
		prep.setString(5, treatment.getTreatmentType());
		prep.setString(6, treatment.getDose());
		prep.setString(7, treatment.getPrescriber().getName());
		prep.setInt(8, treatment.getId());
		prep.executeUpdate();
		return treatment;	
	}
	
}
