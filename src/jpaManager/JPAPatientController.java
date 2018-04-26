package jpaManager;

import java.util.List;

import javax.persistence.*;

import interfaces.PatientInterface;
import model.Nurse;
import model.Patient;

public class JPAPatientController implements PatientInterface{
	
	private static JPAPatientController singleton;
	
	public static JPAPatientController getPatientController() {
		if(singleton==null) {
			singleton = new JPAPatientController();
		}
		return singleton;
	}
	
	public boolean insertCompletePatient(Patient patient) throws Exception{
		EntityManager em =DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		return true;
	}
	
	public boolean deletePatient (Patient patient) throws Exception{
		EntityManager em =DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
		return true;
	}
	
	public List<Patient> getAllPatients() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient", Patient.class);
		List <Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
	}
	
	public Patient searchPatientById (Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE id LIKE ?", Patient.class);
		q1.setParameter(1, id);
		Patient patient = (Patient) q1.getSingleResult();
		return patient;
	}
	
	public void updatePatient(Patient patient) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
	}
	
	public boolean addNurseToPatient (Nurse nurse, Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		patient.addNurse(nurse);
		em.getTransaction().commit();
		return true;
	}
	
	
}
