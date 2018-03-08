package jdbcManager;
import java.sql.*;

import model.Bills;
public class BillsController implements BillsInterface{
	
private static BillsController singleton;
	public BillsController getBillsController () {
	 if (singleton == null) {
		 singleton = new BillsController ();
	 }
	return singleton;
}
	
	public boolean insertBills (Bills bill) throws Exception{
		String sql = "INSERT INTO bill (id, totalCost, bankID, paid)+VALUES (?,?,?,?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, bill.getId());
		prep.setFloat(2, bill.getTotalCost());
		prep.setString(3, bill.getBankID());
		prep.setBoolean(4, bill.getPaid());
		prep.executeUpdate();
		prep.close();
		return true;
		
		
	}
public boolean deleteBills (Bills bill)  throws Exception{
	String sql = "DELETE FROM bill WHERE id=?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1,  bill.getId());
	prep.executeUpdate();
	return true;
}
public Bills searchBills (Integer id) throws Exception{
	

	Statement stmt = DBConnection.getConnection().createStatement();
	String sql = "SELECT * FROM bill";
	ResultSet rs = stmt.executeQuery(sql);
	while (rs.next()) {
		int Id = rs.getInt("id");
		float totalCost = rs.getFloat("totalCost");
		String bankID = rs.getString("bankID");
		boolean paid = rs.getBoolean("paid");
		Bills bill = new Bills (); 
		return bill;
	}
	rs.close();
	stmt.close();
	return null;
}
	
public Bills updateBills (Bills bill) throws Exception {
	String sql = "UPDATE * FROM bill WHERE id = ?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1, bill.getId());
	prep.executeUpdate();
	return bill;
	
	
}


}
