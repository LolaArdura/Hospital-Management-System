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

		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		return true;
	
	}
	

	public boolean insertNoDiagnosePatient(Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

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
			patient.setName(patient.getName());
			patient.setDob(patient.getDob());
			patient.setDateAdmission(patient.getDateAdmission());
			patient.setDiagnose(patient.getDiagnose());
			patient.setGender(patient.getGender());
		em.getTransaction().commit();

	}
	
	public void addNurseToPatient (Nurse nurse, Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		patient.addNurse(nurse);
		nurse.addPatientToNurse(patient);
		em.getTransaction().commit();

	}
	
	public void deleteNurseFromPatient (Nurse nurse, Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		patient.removeNurse(nurse);
		nurse.deletePatientFromNurse(patient);
		em.getTransaction().commit();
	
	}
	
	public List<Patient> getPatientWithoutTreatmentsAndBills () throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT id, name, gender, dob, dateAdmission, room_id FROM patient", Patient.class);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		
	}
	
	public List<Bills> getBillsFromPatient (Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM bills JOIN patient WHERE bills.patient_id ="+patient.getId(), Bills.class);
		List <Bills> bills = (List<Bills>)q1.getResultList();
		
		Query q2= em.createNativeQuery("SELECT bills.id, bills.cost,bankID,paid FROM bills JOIN treatment ON bills.id=treatment.bill_id"
				+ "WHERE treatment.patient_id="+patient.getId(),Bills.class);
		bills.addAll((List<Bills>)q2.getResultList());
		return bills;
		
	}
	
	public List<Treatment> getTreatmentsFromPatient(Patient patient) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("Select * FROM treatment JOIN patient WHERE treatment.patient_id="+patient.getId(), Treatment.class);
		List<Treatment> treatments = (List<Treatment>)q1.getResultList();
		return treatments;

	}
	
	public List<Patient> searchPatientByName (String name) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, name);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		
	}

	@Override
	public void addRoomToPatient(Patient patient, Room room) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		patient.setRoom(room);
		room.addPatient(patient);
		em.getTransaction().commit();
		
	}
	
	
}
