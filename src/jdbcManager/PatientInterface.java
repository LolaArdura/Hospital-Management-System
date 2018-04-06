package jdbcManager;
import java.util.List;

import model.Patient;

public interface PatientInterface {
	public boolean insertCompletePatient ( Patient patient) throws Exception;
	public boolean deletePatient (Patient patient) throws Exception;
	public List<Patient> getAllPatients() throws Exception;
	public Patient searchPatientById(Integer id) throws Exception;
	public Patient updatePatient (Patient patient) throws Exception;
	
}
