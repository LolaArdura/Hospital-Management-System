package jpaManager;

import javax.persistence.EntityManager;

import model.Nurse;

public class JPANurseController {
	
	
private static JPANurseController singleton;
	
	public static JPANurseConotroller getNurseController() {
		if (singleton==null) {
			singleton =new JPANurseController();
		}
		return singleton;
	}
	
	public boolean insertNurse (Nurse nurse) throws Exception{
		EntityManager em=DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(nurse);
		em.getTransaction().commit();
		return true;
}
	
	public boolean deleteNurse (Nurse nurse) throws Exception{
		EntityManager em=DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(nurse);
		em.getTransaction().commit();
		return true;
	}
}
