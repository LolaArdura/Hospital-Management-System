package jdbcManager;
import java.sql.*;
import java.io.*;
import model.Doctor;

public class DoctorController implements DoctorInterface {
	private DoctorController() {
		super();
	}
	private static DoctorController singleton;
	public DoctorController getNurseController() {
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
		File photo = new File ("./photos/" + doctor.getPhoto());
		if (doctor.getPhoto()!=null) {
			InputStream streamBlob = new FileInputStream(photo);
			byte[] bytesBlob = new byte[streamBlob.available()];
			streamBlob.read(bytesBlob);
			streamBlob.close();
			prep.setBytes(3, bytesBlob);
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
	public Doctor searchDoctorById (Integer id) throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM doctor";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int Id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			String schedule = rs.getString("schedule");
			String speciality = rs.getString("speciality");
			Doctor doctor = new Doctor (Id, name, photo, schedule, speciality);
			return doctor;
		}
		stmt.close();
		return null;
	}
	public Doctor updateDoctor (Doctor doctor) throws Exception{
		String sql = "UPDATE * FROM doctor WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, doctor.getId());
		prep.executeUpdate();
		return doctor;
	}
}
