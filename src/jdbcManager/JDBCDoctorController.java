package jdbcManager;
import interfaces.*;
import java.sql.*;
import java.util.*;
import model.Doctor;
import model.Nurse;

public class JDBCDoctorController implements DoctorInterface {
	private JDBCDoctorController() {
		super();
	}

	private static JDBCDoctorController singleton;

	public static JDBCDoctorController getDoctorController() {
		if (singleton == null) {
			singleton = new JDBCDoctorController();
		}
		return singleton;
	}

	
	public boolean insertDoctor(Doctor doctor) throws Exception {
		String sql= "INSERT INTO doctor (name,photo,schedule,specialty) VALUES (?,?,?,?)";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, doctor.getName());
		if(doctor.getPhoto() != null) {
			prep.setBytes(2,doctor.getPhoto());
		}else {
			prep.setBytes(2, null);
		}
		prep.setString(3, doctor.getSchedule());
		prep.setString(4,doctor.getSpeciality());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public boolean deleteDoctor(Doctor doctor) throws Exception {
		String sql = "DELETE FROM doctor WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, doctor.getId());
		prep.executeUpdate();
		return true;
	}
	
	//wE WONT use this
	public boolean deleteDoctorWithoutId(Doctor doctor) throws Exception{
		String sql="DELETE FROM doctor WHERE name=? AND schedule = ? AND specialty=?";
		PreparedStatement prep=JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1,doctor.getName());
		prep.setString(2, doctor.getSchedule());
		prep.setString(3,doctor.getSpeciality());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public List<Doctor> getAllDoctors() throws Exception {
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM doctor";
		ResultSet rs = stmt.executeQuery(sql);
		List<Doctor> doctorList = new LinkedList<Doctor>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String speciality = rs.getString("specialty");
			Doctor searchDoctor = new Doctor(Id, name, photo, schedule, speciality);
			doctorList.add(searchDoctor);
		}
		stmt.close();
		return doctorList;
	}

	public Doctor searchDoctorById(Integer id) throws Exception {
		String sql = "SELECT * FROM doctor WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();

		rs.next();
		int Id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String speciality = rs.getString("specialty");
		Doctor doctor = new Doctor(Id, name, photo, schedule, speciality);
		return doctor;

	}
	
	public Doctor searchDoctorByName (String name) throws Exception{
		String sql  ="SELECT * FROM doctor WHERE name=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int id = rs.getInt("id");
		String Name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String specialty = rs.getString("specialty");
		Doctor doctorByName = new Doctor(id, Name, photo, schedule,specialty);
		return doctorByName;
	}
	
	public Doctor searchDoctorBySchedule (String schedule) throws Exception{
		String sql  ="SELECT * FROM doctor WHERE schedule=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, schedule);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String Schedule = rs.getString("schedule");
		String specialty = rs.getString("specialty");
		Doctor doctorBySchedule = new Doctor(id, name, photo, Schedule, specialty);
		return doctorBySchedule;
	}
	
	public Doctor searchDoctorBySpecialty (String specialty) throws Exception {
		String sql = "SELECT * FROM doctor WHERE specialty= ?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, specialty);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		String schedule = rs.getString("schedule");
		String Specialty = rs.getString("specialty");
		Doctor doctorBySchedule = new Doctor(id, name, photo, schedule, Specialty);
		return doctorBySchedule;
		
	}
	

	public Doctor updateDoctor(Doctor doctor) throws Exception {
		String sql = "UPDATE doctor SET name=?, schedule=?, specialty=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, doctor.getName());
		prep.setString(2, doctor.getSchedule());
		prep.setString(3, doctor.getSpeciality());
		prep.setInt(4, doctor.getId());
		prep.executeUpdate();
		return doctor;
	}
}