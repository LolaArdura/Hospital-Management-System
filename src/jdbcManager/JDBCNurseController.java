package jdbcManager;
import interfaces.*;
import java.sql.*;
import java.util.*;
import model.Nurse;

public class JDBCNurseController implements NurseInterface {
	private JDBCNurseController() {
		super();
	}

	private static JDBCNurseController singleton;

	public static JDBCNurseController getNurseController() {
		if (singleton == null) {
			singleton = new JDBCNurseController();
		}
		return singleton;
	}

	public boolean insertNurse(Nurse nurse) throws Exception {
		String sql = "INSERT INTO nurse (name, photo, schedule, role) " + "VALUES (?,?,?,?);";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, nurse.getName());
		if (nurse.getPhoto() != null) {
			prep.setBytes(2, nurse.getPhoto());
		} else {
			prep.setBytes(2, null);
		}
		prep.setString(3, nurse.getSchedule());
		prep.setString(4, nurse.getRole());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public boolean deleteNurse(Nurse nurse) throws Exception {
		String sql = "DELETE FROM nurse WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public List<Nurse> getAllNurses() throws Exception {
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM nurse";
		ResultSet rs = stmt.executeQuery(sql);
		List<Nurse> nurseList = new LinkedList<Nurse>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse(Id, name, photo, schedule, role);
			nurseList.add(searchNurse);
		}
		stmt.close();
		return nurseList;
	}

	public Nurse searchNurseById(Integer id) throws Exception {
		String sql = "SELECT * FROM nurse WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int Id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String role = rs.getString("role");
		Nurse searchNurse = new Nurse(Id, name, photo, schedule, role);
		return searchNurse;
	}
	
	public Nurse searchNurseBySchedule (String schedule) throws Exception{
		String sql  ="SELECT * FROM nurse WHERE schedule=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, schedule);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String Schedule = rs.getString("schedule");
		String role = rs.getString("role");
		Nurse nurseBySchedule = new Nurse(id, name, photo, Schedule, role);
		return nurseBySchedule;
	}
	
	public Nurse searchNurseByName (String name) throws Exception{
		String sql  ="SELECT * FROM nurse WHERE name=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int id = rs.getInt("id");
		String Name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String role = rs.getString("role");
		Nurse nurseByName = new Nurse(id, Name, photo, schedule, role);
		return nurseByName;
	}
	
	public Nurse updateNurse(Nurse nurse) throws Exception {
		String sql = "UPDATE nurse SET name=?, schedule=?, role=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, nurse.getName());
		prep.setString(2, nurse.getSchedule());
		prep.setString(3, nurse.getRole());
		prep.setInt(4, nurse.getId());
		prep.executeUpdate();
		return nurse;
	}
}
