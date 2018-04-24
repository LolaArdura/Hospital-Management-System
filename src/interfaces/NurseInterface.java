package interfaces;
import java.util.List;

import model.Nurse;
import model.Patient;
	
public interface NurseInterface {
	public boolean insertNurse (Nurse nurse) throws Exception;
	public boolean deleteNurse (Nurse nurse) throws Exception;
	public List<Nurse> getAllNurses () throws Exception;
	public Nurse searchNurseById (Integer id) throws Exception;
	public List<Nurse> searchNurseBySchedule (String schedule) throws Exception;
	public List<Nurse> searchNurseByName (String name) throws Exception;
	public List<Nurse> searchNurseByRole (String role) throws Exception;
	public void updateNurse (Nurse nurse) throws Exception;
	public List<Patient> getPatientsFromNurse(Nurse nurse) throws Exception;
	public void addPatientToNurse(Nurse nurse, Patient patient) throws Exception;
	public void deletePatientFromNurse(Nurse nurse,Patient patient) throws Exception;
}