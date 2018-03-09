package jdbcManager;
import java.sql.*;
import java.util.*;
import model.Doctor;

public class DoctorController implements DoctorInterface{
	private DoctorController() {
		super();
	}
	
	private static DoctorController singleton;
	public static DoctorController getDoctorController() {
		if (singleton == null) {
			singleton = new DoctorController();
		}
		return singleton;
	}
	
	public boolean insertDoctor (Doctor doctor) throws Exception {
		String sql = "INSERT INTO doctor (id, name, photo, schedule, speciality) " 
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, doctor.getId());
		prep.setString(2, doctor.getName());
		if (doctor.getPhoto()!=null) {
			prep.setBytes(3, doctor.getPhoto());
		}
		else {
			prep.setBytes(3,  null);
		}
		prep.setString(4, doctor.getSchedule());
		prep.setString(5, doctor.getSpeciality());
		prep.executeUpdate();
		prep.close();
		return true;
	}

	public boolean deleteDoctor (Doctor doctor) throws Exception {
		String sql = "DELETE FROM doctor WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, doctor.getId());
		prep.executeUpdate();
		return true;
	}
	
	public List<Doctor> getAllDoctors () throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM doctor";
		ResultSet rs = stmt.executeQuery(sql);
		List<Doctor> doctorList= new LinkedList <Doctor>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String speciality = rs.getString("speciality");
			Doctor searchDoctor = new Doctor (Id, name, photo, schedule, speciality);
			doctorList.add(searchDoctor);
		}
		stmt.close();
		return doctorList;
	}
	
	public Doctor searchDoctorById (Integer id) throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT FROM doctor WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = stmt.executeQuery(sql);
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String speciality = rs.getString("speciality");
			Doctor doctor = new Doctor (Id, name, photo, schedule, speciality);
		stmt.close();
		return doctor;
	}
	
	public Doctor updateDoctor (Doctor doctor) throws Exception{
		String sql = "UPDATE doctor SET name=?, schedule=?, speciality=? WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setString(1, doctor.getName());
		prep.setString(2, doctor.getSchedule());
		prep.setString(3, doctor.getSpeciality());
		prep.setInt(4, doctor.getId());
		prep.executeUpdate();
		return doctor;
	}
}