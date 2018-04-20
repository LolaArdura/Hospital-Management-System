package interfaces;
import java.util.List;

import model.Nurse;
	
public interface NurseInterface {
	public boolean insertNurse (Nurse nurse) throws Exception;
	public boolean deleteNurse (Nurse nurse) throws Exception;
	public List<Nurse> getAllNurses () throws Exception;
	public Nurse searchNurseById (Integer id) throws Exception;
	public Nurse searchNurseBySchedule (String schedule) throws Exception;
	public Nurse searchNurseByName (String name) throws Exception;
	public void updateNurse (Nurse nurse) throws Exception;
}