package jpaManager;

import javax.persistence.EntityManager;

import interfaces.*;
import model.Room;
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
	 public boolean insertRoom (Treatment treatment) throws Exception{
		 EntityManager em =DBEntityManager.getEntityManager();
		 em.getTransaction().begin();
		 em.persist(treatment);
		 em.getTransaction().commit();
		 return true;
		 
	 }
	 
}
