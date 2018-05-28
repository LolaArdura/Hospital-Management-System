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
	public boolean insertNoDiagnosePatient(Patient patient) throws Exception;
	public void deleteNurseFromPatient(Nurse nurse, Patient patient) throws Exception;
	public List<Patient> getPatientWithoutTreatmentsAndBills() throws Exception;
	public List<Bills> getBillsFromPatient (Patient patient) throws Exception;
	public List<Treatment> getTreatmentsFromPatient (Patient patient) throws Exception;
	public List<Patient> searchPatientByName(String name) throws Exception;
	public void addRoomToPatient(Patient patient, Room room) throws Exception;
}
