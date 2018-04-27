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
		em.getTransaction().begin();
		em.persist(nurse);
		em.getTransaction().commit();
		return true;
}
	
	public boolean deleteNurse (Nurse nurse) throws Exception{
		EntityManager em=DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(nurse);
		em.getTransaction().commit();
		return true;
	}
	
	public Nurse searchNurseById (Integer id) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//search the nurse by id
		Query q1 = em.createNativeQuery("SELECT * FROM nurse WHERE id LIKE ?", Nurse.class);
		q1.setParameter(1, id );
		Nurse nurse = (Nurse)q1.getSingleResult();
		return nurse;
	}
	
	public List <Nurse> searchNurseBySchedule  (String schedule) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//search the nurse by schedule
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE schedule LIKE ?", Nurse.class);
		q1.setParameter(1, schedule);
		List<Nurse> nurses = (List<Nurse>)q1.getResultList();
		return nurses;
	}
	
	public List<Nurse> searchNurseByName (String name) throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		//search the nurse by schedule
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE name LIKE ?", Nurse.class);
		q1.setParameter(1, name);
		List <Nurse> nurse = (List<Nurse>)q1.getResultList();
		return nurse;
	}
	
	public List<Nurse> searchNurseByRole (String role) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1= em.createNativeQuery("SELECT * FROM nurse WHERE role LIKE ?", Nurse.class);
		q1.setParameter(1, role);
		List <Nurse> nurse = (List<Nurse>)q1.getResultList();
		return nurse;
		
	}
	
	public List<Nurse> getAllNurses() throws Exception{
		//get the entity manager
		EntityManager em= DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		//Select all nurses 
		Query q1 = em.createNativeQuery("SELECT * FROM nurse", Nurse.class);
		List<Nurse> nurses = (List<Nurse>)q1.getResultList();
		return nurses;
	}
	
	
	@Override
	public void updateNurse (Nurse nurse) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction();
		em.flush();
		em.getTransaction().commit();
	}
	
	
	public void addPatientToNurse (Nurse nurse, Patient patient)throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		nurse.addPatientToNurse(patient);
		em.getTransaction().commit();
				
	}
	
	public List <Patient> getPatientsFromNurse (Nurse nurse )throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_key=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1  = em.createNativeQuery("SELECT id,name FROM patient AS p JOIN nurse_patient WHERE nurse_patient.nurse_id ="+nurse.getId(),
				Patient.class);
		List <Patient> patients = (List <Patient>)q1.getResultList();
		return patients;
		
	}



	
	public void deletePatientFromNurse(Nurse nurse, Patient patient) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		nurse.deletePatientFromNurse(patient);
		em.getTransaction().commit();
	}
	
}
