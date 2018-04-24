

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
	
public void updateBills (Bills bill) throws Exception {
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




}
