package jdbcManager;
import java.util.List;

import model.Nurse;
	
public interface NurseInterface {
	public boolean insertNurse (Nurse nurse) throws Exception;
	public boolean deleteNurse (Nurse nurse) throws Exception;
	public List<Nurse> getAllNurses () throws Exception;
	public Nurse searchNurseById (Integer id) throws Exception;
	public Nurse updateNurse (Nurse nurse) throws Exception;
}