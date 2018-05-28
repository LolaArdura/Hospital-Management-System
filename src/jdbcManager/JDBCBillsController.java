

package jdbcManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import interfaces.*;
import model.*;

public class JDBCBillsController implements BillsInterface{
	
private static JDBCBillsController singleton;
	public static JDBCBillsController getBillsController () {
	 if (singleton == null) {
		 singleton = new JDBCBillsController ();
	 }
	return singleton;
}
	
	public boolean insertBills (Bills bill) throws Exception{
		String sql = "INSERT INTO bills ( cost, bankID, paid, patient_id) "
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
	prep.close();
	return true;
	
	
}
public Bills searchBillsById (Integer id) throws Exception{
	String sql = "SELECT * FROM bills WHERE id=?";
	PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	rs.next();
	int Id = rs.getInt("id");
	float totalCost = rs.getFloat("cost");
	String bankID = rs.getString("bankID");
	boolean paid = rs.getBoolean("paid");
	Bills bill = new Bills (Id, totalCost, bankID, paid);
	rs.close();
    prep.close();
	return bill;
	
}
	
public void updateBills (Bills bill) throws Exception {
	String sql = "UPDATE  bills"
			+ "SET bankID = ? ,"
			+ "cost = ?, "
			+ "paid = ?"
			+ "WHERE id = ?";
	PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

	prep.setString(1, bill.getBankID());
	prep.setFloat(2, bill.getTotalCost());
	prep.setBoolean(3, bill.getPaid());
	prep.executeUpdate();
	prep.close();
  
}

}
