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
		String sql = "INSERT INTO bills (id, totalCost, bankID, paid)+VALUES (?,?,?,?)";
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
	String sql = "DELETE FROM bills WHERE id=?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1,  bill.getId());
	prep.executeUpdate();
	return true;
}
public Bills searchBillsById (Integer id) throws Exception{
	

	Statement stmt = DBConnection.getConnection().createStatement();
	String sql = "SELECT FROM bills WHERE id=?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = stmt.executeQuery(sql);
	
	
	int Id = rs.getInt("id");
	float totalCost = rs.getFloat("totalCost");
	String bankID = rs.getString("bankID");
	boolean paid = rs.getBoolean("paid");
	Bills bill = new Bills (Id, totalCost, bankID, paid);
		
		
	
	stmt.close();
	return bill;
	
}
	
public Bills updateBills (Bills bill) throws Exception {
	String sql = "UPDATE  bills"
			+ "SET bankID = ? ,"
			+ "totalCost = ?, "
			+ "paid = ?"
			+ "WHERE id = ?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setString(1, bill.getBankID());
	prep.setFloat(2, bill.getTotalCost());
	prep.setBoolean(3, bill.getPaid());
	prep.executeUpdate();
	return bill;
}
 public static void main (String args[]) {
	 try {
		 
		 
	 }catch (Exception e) {
		 
		 
	 }
	 
	 
 }

}
