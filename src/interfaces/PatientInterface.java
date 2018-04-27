package interfaces;
import java.util.List;

import model.*;

public interface PatientInterface {
	public boolean insertCompletePatient ( Patient patient) throws Exception;
	public boolean deletePatient (Patient patient) throws Exception;
	public List<Patient> getAllPatients() throws Exception;
	public Patient searchPatientById(Integer id) throws Exception;
	public void updatePatient (Patient patient) throws Exception;
	public void addNurseToPatient (Nurse nurse, Patient patient) throws Exception;
	public void deleteNurseFromPatient(Nurse nurse, Patient patient) throws Exception;
	
}
