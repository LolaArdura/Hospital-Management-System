package jdbcManager;
import model.Nurse;
	
public interface NurseInterface {
	public boolean insertNurse (Nurse nurse) throws Exception;
	public boolean deleteNurse (Nurse nurse) throws Exception;
	public Nurse searchNurse (Integer id) throws Exception;
	public Nurse updateNurse (Nurse nurse) throws Exception;
}