package jpaManager;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.*;
import model.Treatment;
import jdbcManager.JDBCTreatmentController;

public class JPATreatmentController implements RoomInterface {
	 private static JPATreatmentController singleton;
	 
	 public static JPATreatmentController getTreatmentController() {
		 if(singleton ==null) {
			 singleton= new JPATreatmentController();
		 }
		 return singleton;
	 }
	 public boolean insertTreatment (Treatment treatment) throws Exception{
		 EntityManager em =DBEntityManager.getEntityManager();
		 em.getTransaction().begin();
		 em.persist(treatment);
		 em.getTransaction().commit();
		 return true;
		 
	 }
	 
	 public boolean deleteTreatment (Treatment treatment) throws Exception{
		 
		 EntityManager em =DBEntityManager.getEntityManager();
		 em.getTransaction().begin();
		 em.remove(treatment);
		 em.getTransaction().commit();
		 return true;
		 
	 }
	 
	 
	public Treatment searchTreatmentById (Integer id) throws Exception{
		// Get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMAM foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//Search the treatments by id
		Query q1 = em.createNativeQuery("SELECT * FROM treatments WHERE id LIKE ?", Treatment.class);
		q1.setParameter(1, id );
		Treatment treatment = (Treatment)q1.getSingleResult();
		return treatment;
		
		}
}
