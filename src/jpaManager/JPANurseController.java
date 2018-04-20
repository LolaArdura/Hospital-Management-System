package jpaManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Nurse;
import model.Treatment;

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
	
	public Nurse searchNurseById (Integer id) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//search the nurse by id
		Query q1 = em.createNativeQuery("SELECT * FROM nurse WHERE id LIKE ?", Nurse.class);
		q1.setParameter(1, id );
		Nurse nurse = (Nurse)q1.getSingleResult();
		return nurse;
	}
	
	public Nurse searchNurseBySchedule  (String schedule) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//search the nurse by schedule
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE schedule LIKE ?", Nurse.class);
		q1.setParameter(1, schedule);
		Nurse nurse = (Nurse)q1.getSingleResult();
		return nurse;
	}
	
	public Nurse searchNurseByName (String name) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		//search the nurse by schedule
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE name LIKE ?", Nurse.class);
		q1.setParameter(1, name);
		Nurse nurse = (Nurse)q1.getSingleResult();
		return nurse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
