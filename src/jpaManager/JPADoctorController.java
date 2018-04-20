package jpaManager;

import java.util.*;

import javax.persistence.*;

import interfaces.DoctorInterface;
import model.Doctor;

public class JPADoctorController implements DoctorInterface{

	private static JPADoctorController singleton;
	
	public static JPADoctorController getJPADoctorController() {
		if(singleton == null) {
			singleton = new JPADoctorController();
		}
		return singleton;
	}
	
	public boolean insertDoctor(Doctor doctor) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(doctor);
		em.getTransaction().commit();
		return true;
	}
	
	public boolean deleteDoctor(Doctor doctor) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(doctor);
		em.getTransaction().commit();
		return true;
	}
	
	public List<Doctor> getAllDoctors() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor", Doctor.class);
		List <Doctor> doctors = (List<Doctor>) q1.getResultList();
		return doctors;
	}
}
