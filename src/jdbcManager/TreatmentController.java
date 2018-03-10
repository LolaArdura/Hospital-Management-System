package jdbcManager;

import java.sql.*;

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
		String sql = "INSERT INTO treatment (id, routeOfAdmin, startDate, endDate, cost, treatmentType, dose, prescriber)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, treatment.getId());
		prep.setString(2,treatment.getRouteOfAdmin());
		prep.setDate(3, treatment.getStartDate());
		prep.setDate(4, treatment.getEndDate());
		prep.setFloat(5, treatment.getCost());
		prep.setString(6, treatment.getTreatmentType());
		prep.setString(7, treatment.getDose());
		prep.setString(8, treatment.getPrescriber());
	}
	
	public Treatment updateTreatment (Treatment treatment) throws Exception {
		String sql = "UPDATE FROM treatment WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, treatment.getId());
		prep.executeUpdate();
		return treatment;
		
	}
	
}
