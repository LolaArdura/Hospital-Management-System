package jdbcManager;

import java.sql.*;
import java.util.*;
import model.Nurse;

public class NurseController implements NurseInterface {
	private NurseController() {
		super();
	}

	private static NurseController singleton;

	public static NurseController getNurseController() {
		if (singleton == null) {
			singleton = new NurseController();
		}
		return singleton;
	}

	public boolean insertNurse(Nurse nurse) throws Exception {
		String sql = "INSERT INTO nurse (id, name, photo, schedule, role) " + "VALUES (?,?,?,?,?);";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.setString(2, nurse.getName());
		if (nurse.getPhoto() != null) {
			prep.setBytes(3, nurse.getPhoto());
		} else {
			prep.setBytes(3, null);
		}
		prep.setString(4, nurse.getSchedule());
		prep.setString(5, nurse.getRole());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public boolean deleteNurse(Nurse nurse) throws Exception {
		String sql = "DELETE FROM nurse WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		return true;
	}

	public List<Nurse> getAllNurses() throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
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
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
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

	public Nurse updateNurse(Nurse nurse) throws Exception {
		String sql = "UPDATE nurse SET name=?, schedule=?, role=? WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, nurse.getName());
		prep.setString(2, nurse.getSchedule());
		prep.setString(3, nurse.getRole());
		prep.setInt(4, nurse.getId());
		prep.executeUpdate();
		return nurse;
	}
}
