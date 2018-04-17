

package jdbcManager;
import java.sql.*;
import interfaces.*;

import model.Bills;
public class JDBCBillsController implements BillsInterface{
	
private static JDBCBillsController singleton;
	public static JDBCBillsController getBillsController () {
	 if (singleton == null) {
		 singleton = new JDBCBillsController ();
	 }
	return singleton;
}
	
	public boolean insertBills (Bills bill, Integer patientId) throws Exception{
		String sql = "INSERT INTO bills (id, totalCost, bankID, paid, patient_id) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, bill.getId());
		prep.setFloat(2, bill.getTotalCost());
		prep.setString(3, bill.getBankID());
		prep.setBoolean(4, bill.getPaid());
		prep.setInt(5,bill.getPatient().getId());
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

	String sql = "SELECT * FROM bills WHERE id=?";
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
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
	PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
	prep.setString(1, bill.getBankID());
	prep.setFloat(2, bill.getTotalCost());
	prep.setBoolean(3, bill.getPaid());
	prep.executeUpdate();
	return bill;
}


}
