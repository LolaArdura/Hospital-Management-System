package jdbcManager;
import java.util.List;

import model.Patient;

public interface PatientInterface {
	public boolean insertPatient ( Patient patient) throws Exception;
	public boolean deletePatient (Patient patient) throws Exception;
	public List searchpatient () throws Exception;
	public Patient updatePatient (Patient patient) throws Exception;
	
}
