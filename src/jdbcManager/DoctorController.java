package jdbcManager;
import java.sql.*;
import java.io.*;
import model.Doctor;
import model.Nurse;

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
}
