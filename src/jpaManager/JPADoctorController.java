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
		EntityTransaction t= em.getTransaction();
		t.begin();
		em.persist(doctor);
		t.commit();
		return true;
		}
	
	public boolean deleteDoctor(Doctor doctor) throws Exception{
		    EntityManager em = DBEntityManager.getEntityManager();
			Doctor doctorReceived=JPADoctorController.getJPADoctorController().searchDoctorById(doctor.getId());
			em.getTransaction().begin();
			em.remove(doctorReceived);
			em.getTransaction().commit();
		return true;
	}
	
	public Doctor searchDoctorById (Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE id = ?", Doctor.class);
		q1.setParameter(1, id);
		Doctor doctor = (Doctor) q1.getSingleResult();
		return doctor;
		
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


	@Override
	public List<Doctor> searchDoctorByName(String name) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE name LIKE ?", Doctor.class);
		q1.setParameter(1, name);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
	
	}

	@Override
	public List<Doctor> searchDoctorBySpecialty(String specialty) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE specialty LIKE ?", Doctor.class);
		q1.setParameter(1, specialty);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
		
	}

	@Override
	public List<Doctor> searchDoctorBySchedule(String schedule) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE schedule LIKE ?", Doctor.class);
		q1.setParameter(1, schedule);
		List<Doctor> doctor = (List<Doctor>) q1.getResultList();
		return doctor;
		
	}

	@Override
	public void updateDoctor(Doctor doctor) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();

		em.getTransaction().begin();
			doctor.setName(doctor.getName());
			doctor.setPhoto(doctor.getPhoto());
			doctor.setSchedule(doctor.getSchedule());
			doctor.setSpecialty(doctor.getSpecialty());
		em.getTransaction().commit();

	}
	
	
	
}
