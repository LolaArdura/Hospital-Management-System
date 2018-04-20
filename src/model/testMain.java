package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class testMain {

	public static void main(String[] args) {
		EntityManager em= Persistence.createEntityManagerFactory("hospital-management").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		Doctor doctor=new Doctor(123,"John","morning","cardiology");
		em.getTransaction().begin();
		em.persist(doctor);
		em.getTransaction().commit();
		
		Query q1 = em.createNativeQuery("SELECT * FROM doctor", Doctor.class);
		List<Doctor> doctors= (List<Doctor>) q1.getResultList();
		
		for(Doctor d: doctors) {
		    System.out.println(d);
		}
		
	}
}
