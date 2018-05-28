package jdbcManager;
import interfaces.*;
import java.sql.*;
import java.util.*;
import model.Nurse;
import model.Patient;

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
			int id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse(id, name, photo, schedule, role);
			nurseList.add(searchNurse);
		}
		rs.close();
		stmt.close();
		return nurseList;

	}

	public Nurse searchNurseById(Integer id) throws Exception {
		String sql = "SELECT * FROM nurse WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if(rs.next()) {
		int Id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String role = rs.getString("role");
		Nurse searchNurse = new Nurse(Id, name, photo, schedule, role);
		rs.close();
		prep.close();
		return searchNurse;
		}
		
		else {
			rs.close();
			prep.close();
			return null;
		}

	}
	
	public List<Nurse> searchNurseBySchedule (String schedule) throws Exception{
		String sql  ="SELECT * FROM nurse WHERE schedule=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, schedule);
		ResultSet rs = prep.executeQuery();
		List<Nurse> nurseList = new LinkedList<Nurse>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String scheduleRs = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse(Id, name, photo, scheduleRs, role);
			nurseList.add(searchNurse);
		}
		rs.close();
		prep.close();
		return nurseList;

	}
	
	public List<Nurse> searchNurseByName (String name) throws Exception{
		String sql  ="SELECT * FROM nurse WHERE name=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		List<Nurse> nurseList = new LinkedList<Nurse>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String nameRs = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String role = rs.getString("role");
			Nurse searchNurse = new Nurse(Id, nameRs, photo, schedule, role);
			nurseList.add(searchNurse);
		}
		rs.close();
		prep.close();
		return nurseList;

	}
	
	public List<Nurse> searchNurseByRole( String role) throws Exception{
		String sql  ="SELECT * FROM nurse WHERE role=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, role);
		ResultSet rs = prep.executeQuery();
		List<Nurse> nurseList = new LinkedList<Nurse>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String roleRs = rs.getString("role");
			Nurse searchNurse = new Nurse(Id, name, photo, schedule, roleRs);
			nurseList.add(searchNurse);
		}
		rs.close();
		prep.close();
		return nurseList;

	}
	
	public void updateNurse(Nurse nurse) throws Exception {
		String sql = "UPDATE nurse SET name=?, photo=?, schedule=?, role=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);

		prep.setString(1, nurse.getName());
		prep.setBytes(2, nurse.getPhoto());
		prep.setString(3, nurse.getSchedule());
		prep.setString(4, nurse.getRole());
		prep.setInt(5, nurse.getId());
		prep.executeUpdate();
		prep.close();

	}

	public List<Patient> getPatientsFromNurse(Nurse nurse) throws Exception {
		String sql="SELECT p.id,p.name FROM patient AS p JOIN nurse_patient ON p.id=nurse_patient.patient_id"
				+ " WHERE nurse_patient.nurse_id=?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);

		prep.setInt(1, nurse.getId());
		ResultSet rs=prep.executeQuery();
		List<Patient> patients=new LinkedList<Patient>();
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			Patient p= new Patient(id,name);
			patients.add(p);
		}
		rs.close();
		prep.close();
		return patients;

	}

	@Override
	public void addPatientToNurse(Nurse nurse, Patient patient) throws Exception {
		String sql="INSERT INTO nurse_patient (nurse_id,patient_id) VALUES (?,?)";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);

		prep.setInt(1, nurse.getId());
		prep.setInt(2, patient.getId());
	    prep.executeUpdate();
	    prep.close();

	}
	
	public void deletePatientFromNurse(Nurse nurse,Patient patient) throws Exception{
		String sql=" DELETE FROM nurse_patient WHERE nurse_id=? AND patient_id=?";
		PreparedStatement prep= JDBConnection.getConnection().prepareStatement(sql);

		prep.setInt(1,nurse.getId());
		prep.setInt(2,patient.getId());
		prep.executeUpdate();
		prep.close();

	}
}
