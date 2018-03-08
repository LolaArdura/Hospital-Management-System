package jdbcManager;
import java.util.List;

import model.Doctor;

public interface DoctorInterface {
	public boolean insertDoctor (Doctor doctor) throws Exception;
	public boolean deleteDoctor (Doctor doctor) throws Exception;
	public List searchDoctor () throws Exception;
	public Doctor updateDoctor (Doctor doctor) throws Exception;
}
