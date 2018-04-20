

package jdbcManager;
import java.sql.*;
import interfaces.*;

import model.Bills;
public class BillsController implements BillsInterface{
	
private static BillsController singleton;
	public static BillsController getBillsController () {
	 if (singleton == null) {
		 singleton = new BillsController ();
	 }
	return singleton;
}
	
	public boolean insertBills (Bills bill, Integer patientId) throws Exception{
		String sql = "INSERT INTO bills ( totalCost, bankID, paid, patient_id) "
				+ "VALUES (?,?,?,?)";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setFloat(1, bill.getTotalCost());
		prep.setString(2, bill.getBankID());
		prep.setBoolean(3, bill.getPaid());
		prep.setInt(4,bill.getPatient().getId());
		prep.executeUpdate();
		prep.close();
		return true;
	
	}
	
public boolean deleteBills (Bills bill)  throws Exception{
	String sql = "DELETE FROM bills WHERE id=?";
	PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1,  bill.getId());
	prep.executeUpdate();
	return true;
}
public Bills searchBillsById (Integer id) throws Exception{

	String sql = "SELECT * FROM bills WHERE id=?";
	PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	
	rs.next();
	int Id = rs.getInt("id");
	float totalCost = rs.getFloat("totalCost");
	String bankID = rs.getString("bankID");
	boolean paid = rs.getBoolean("paid");
	Bills bill = new Bills (Id, totalCost, bankID, paid);

	return bill;
	
}
//FALTA HACER METODO SEARCH BY PATIENT
	
public Bills updateBills (Bills bill) throws Exception {
	String sql = "UPDATE  bills"
			+ "SET bankID = ? ,"
			+ "totalCost = ?, "
			+ "paid = ?"
			+ "WHERE id = ?";
	PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
	prep.setString(1, bill.getBankID());
	prep.setFloat(2, bill.getTotalCost());
	prep.setBoolean(3, bill.getPaid());
	prep.executeUpdate();
	return bill;
}


}
