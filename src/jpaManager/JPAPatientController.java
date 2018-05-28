package jpaManager;

import java.util.List;

import javax.persistence.*;

import interfaces.PatientInterface;
import model.*;

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
		try {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().commit();
			
			throw new Exception();
		}
	}
	
	//NOT SURE
	public boolean insertNoDiagnosePatient(Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public boolean deletePatient (Patient patient) throws Exception{
		EntityManager em =DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public List<Patient> getAllPatients() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient", Patient.class);
		List <Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		}catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public Patient searchPatientById (Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE id LIKE ?", Patient.class);
		q1.setParameter(1, id);
		Patient patient = (Patient) q1.getSingleResult();
		return patient;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public void updatePatient(Patient patient) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();

	}
	
	public void addNurseToPatient (Nurse nurse, Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		patient.addNurse(nurse);
		em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public void deleteNurseFromPatient (Nurse nurse, Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		patient.removeNurse(nurse);
		em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public List<Patient> getPatientWithoutTreatmentsAndBills () throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT id, name, gender, dob, dateAdmission, room_id FROM patient", Patient.class);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public List<Bills> getBillsFromPatient (Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM bills JOIN patient WHERE bills.patient_id ="+patient.getId(), Bills.class);
		List <Bills> bills = (List<Bills>)q1.getResultList();
		return bills;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public List<Treatment> getTreatmentsFromPatient(Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("Select * FROM treatment JOIN patient WHERE treatment.patient_id="+patient.getId(), Treatment.class);
		List<Treatment> treatments = (List<Treatment>)q1.getResultList();
		return treatments;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	public List<Patient> searchPatientByName (String name) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, name);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		}catch(Exception e) {
			em.getTransaction().commit();
			throw new Exception();
		}
	}
	
	
}
