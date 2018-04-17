package interfaces;
import java.util.List;

import model.Doctor;

public interface DoctorInterface {
	public boolean insertDoctor (Doctor doctor) throws Exception;
	public boolean deleteDoctor (Doctor doctor) throws Exception;
	public Doctor searchDoctorById (Integer id) throws Exception;
	public List<Doctor> getAllDoctors () throws Exception; 
	public Doctor updateDoctor (Doctor doctor) throws Exception;
}
