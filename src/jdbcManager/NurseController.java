package jdbcManager;
import java.sql.*;
import java.util.*;
import java.io.*;

import model.Nurse;

public class NurseController implements NurseInterface{
	private NurseController() {
		super();
	}
	private static NurseController singleton;
	public NurseController getNurseController() {
		if (singleton == null) {
			singleton = new NurseController();
		}
		return singleton;
	}
	public boolean insertNurse (Nurse nurse) throws Exception {
		String sql = "INSERT INTO nurse (id, name, photo, schedule, role) " 
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.setString(2, nurse.getName());
		File photo = new File ("./photos/" + nurse.getPhoto());
		if (nurse.getPhoto()!=null) {
			InputStream streamBlob = new FileInputStream(photo);
			byte[] bytesBlob = new byte[streamBlob.available()];
			streamBlob.read(bytesBlob);
			streamBlob.close();
			prep.setBytes(3, bytesBlob);
		}
		else {
			prep.setBytes(3,  null);
		}
		prep.setString(4, nurse.getSchedule());
		prep.setString(5, nurse.getRole());
		prep.executeUpdate();
		prep.close();
		return true;
	}
	public boolean deleteNurse (Nurse nurse) throws Exception {
		String sql = "DELETE FROM nurse WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		return true;
	}
	
	public List<Nurse> searchNurse () throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM nurse";
		ResultSet rs = stmt.executeQuery(sql);
		List<Nurse> nurseList= new LinkedList <Nurse>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse (Id, name, photo, schedule, role);
			nurseList.add(searchNurse);
		}
		stmt.close();
		return nurseList;
	}
	
	public Nurse searchNurseById (Integer id) throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT FROM nurse WHERE id=?";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse (Id, name, photo, schedule, role);
			return searchNurse;
		}
		stmt.close();
		return null;
	}
	
	public Nurse updateNurse (Nurse nurse) throws Exception{
		String sql = "UPDATE FROM nurse WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		return nurse;
	}
}
