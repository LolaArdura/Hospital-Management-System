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
		try {
		em.getTransaction().begin();
		em.persist(doctor);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public boolean deleteDoctor(Doctor doctor) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(doctor);
			em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		 }
	}
	
	public Doctor searchDoctorById (Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE id = ?", Doctor.class);
		q1.setParameter(1, id);
		Doctor doctor = (Doctor) q1.getSingleResult();
		return doctor;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<Doctor> getAllDoctors() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor", Doctor.class);
		List <Doctor> doctors = (List<Doctor>) q1.getResultList();
		return doctors;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}


	@Override
	public List<Doctor> searchDoctorByName(String name) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE name LIKE ?", Doctor.class);
		q1.setParameter(1, name);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}

	@Override
	public List<Doctor> searchDoctorBySpecialty(String specialty) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE specialty LIKE ?", Doctor.class);
		q1.setParameter(1, specialty);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}

	@Override
	public List<Doctor> searchDoctorBySchedule(String schedule) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE schedule LIKE ?", Doctor.class);
		q1.setParameter(1, schedule);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}

	@Override
	public void updateDoctor(Doctor doctor) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	
	
}
