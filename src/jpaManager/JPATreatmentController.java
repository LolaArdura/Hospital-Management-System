package jpaManager;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.*;
import model.Treatment;

public class JPATreatmentController implements TreatmentInterface {
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
		 em.persist(treatment.getBill());
		 em.persist(treatment);
		 em.getTransaction().commit();
		 return true;
	
	 }
	 

	 public void insertTreatmentWithoutBill (Treatment treatment) throws Exception{
		 EntityManager em =DBEntityManager.getEntityManager();

		 em.getTransaction().begin();
		 em.persist(treatment);
		 em.getTransaction().commit();
		
	 }
	 
	 public boolean deleteTreatment (Treatment treatment) throws Exception{
		 
		 EntityManager em =DBEntityManager.getEntityManager();
		 Treatment treatmentRetrieved= searchTreatmentById(treatment.getId());
		 em.getTransaction().begin();
		 em.remove(treatmentRetrieved);
		 em.getTransaction().commit();
		 return true;
	 }
	 
	 
	public Treatment searchTreatmentById (Integer id) throws Exception{
		// Get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//Search the treatments by id
		Query q1 = em.createNativeQuery("SELECT * FROM treatment WHERE id LIKE ?", Treatment.class);
		q1.setParameter(1, id );
		Treatment treatment = (Treatment)q1.getSingleResult();
		return treatment;

	}
	
	
	public void updateTreatment(Treatment treatment) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();

		em.getTransaction().begin();
			treatment.setRouteOfAdmin(treatment.getRouteOfAdmin());
			treatment.setStartDate(treatment.getStartDate());
			treatment.setEndDate(treatment.getEndDate());
			treatment.setCost(treatment.getCost());
			treatment.setTreatmentType(treatment.getTreatmentType());
			treatment.setDose(treatment.getDose());
			treatment.setDoctor(treatment.getPrescriber());
			
			em.getTransaction().commit();
		
	}
		
	}
