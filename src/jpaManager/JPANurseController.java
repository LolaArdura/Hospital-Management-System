package jpaManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.NurseInterface;
import model.Nurse;
import model.Patient;
import model.Treatment;

public class JPANurseController implements NurseInterface{
	
	
private static JPANurseController singleton;
	
	public static JPANurseController getNurseController() {
		if (singleton==null) {
			singleton =new JPANurseController();
		}
		return singleton;
	}
	
	public boolean insertNurse (Nurse nurse) throws Exception{
		EntityManager em=DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(nurse);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
}
	
	public boolean deleteNurse (Nurse nurse) throws Exception{
		EntityManager em=DBEntityManager.getEntityManager();
		try {
			Nurse nurseReceived=JPANurseController.getNurseController().searchNurseById(nurse.getId());
		em.getTransaction().begin();
		em.remove(nurseReceived);
		em.getTransaction().commit();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public Nurse searchNurseById (Integer id) throws Exception{
		EntityManager em= DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM nurse WHERE id LIKE ?", Nurse.class);
		q1.setParameter(1, id );
		Nurse nurse = (Nurse)q1.getSingleResult();
		return nurse;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List <Nurse> searchNurseBySchedule  (String schedule) throws Exception{
		EntityManager em= DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE schedule LIKE ?", Nurse.class);
		q1.setParameter(1, schedule);
		List<Nurse> nurses = (List<Nurse>)q1.getResultList();
		return nurses;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<Nurse> searchNurseByName (String name) throws Exception{
		EntityManager em= DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE name LIKE ?", Nurse.class);
		q1.setParameter(1, name);
		List <Nurse> nurse = (List<Nurse>)q1.getResultList();
		return nurse;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<Nurse> searchNurseByRole (String role) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE role LIKE ?", Nurse.class);
		q1.setParameter(1, role);
		List <Nurse> nurse = (List<Nurse>)q1.getResultList();
		return nurse;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<Nurse> getAllNurses() throws Exception{
		EntityManager em= DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM nurse", Nurse.class);
		List<Nurse> nurses = (List<Nurse>)q1.getResultList();
		return nurses;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	
	@Override
	public void updateNurse (Nurse nurse) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction();
		em.flush();
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	
	public void addPatientToNurse (Nurse nurse, Patient patient)throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		nurse.addPatientToNurse(patient);
		patient.addNurse(nurse);
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List <Patient> getPatientsFromNurse (Nurse nurse )throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1  = em.createNativeQuery("SELECT id,name FROM patient AS p JOIN nurse_patient WHERE nurse_patient.nurse_id ="+nurse.getId(),
				Patient.class);
		List <Patient> patients = (List <Patient>)q1.getResultList();
		return patients;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}



	
	public void deletePatientFromNurse(Nurse nurse, Patient patient) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		nurse.deletePatientFromNurse(patient);
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
}
