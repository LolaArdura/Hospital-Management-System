package jdbcManager;
import model.Doctor;
import model.Nurse;

public interface DoctorInterface {
	public boolean insertDoctor (Doctor doctor) throws Exception;
	public boolean deleteDoctor (Doctor doctor) throws Exception;
	public Nurse searchDoctor (Integer id);
	public Nurse updateDoctor (Doctor doctor);
}
