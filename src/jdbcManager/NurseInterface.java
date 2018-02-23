package jdbcManager;
import model.Nurse;
	
public interface NurseInterface {
	public boolean insertNurse (Nurse nurse);
	public boolean deleteNurse (Nurse nurse);
	public Nurse searchNurse (Integer id);
	public Nurse updateNurse (Nurse nurse);
	
	}