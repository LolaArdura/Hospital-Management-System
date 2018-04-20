package jdbcManager;

import interfaces.*;

public class JPANurseController implements NurseInterface  {
	private static JPANurseController singleton;
	
	public static JPANurseConotroller getNurseController() {
		if (singleton==null) {
			singleton =new JPANurseController();
		}
		return singleton;
	}
	
	public boolean insertNurse (Nurse nurse) throws Exception
}
