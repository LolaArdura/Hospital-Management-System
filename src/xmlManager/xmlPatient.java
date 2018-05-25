package xmlManager;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import jpaManager.DBEntityManager;
import model.Doctor;
import model.Patient;
import model.Room;
import model.Treatment;
import model.Bills;
import model.Nurse;
import jpaManager.*;


public class xmlPatient {
	
	
public static void marshal (Patient p, String route) throws Exception{
		
	
	EntityManager em=DBEntityManager.getEntityManager();
	
	em = Persistence.createEntityManagerFactory("hospital-management").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
			
	JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
	Marshaller marshaller = jaxbContext.createMarshaller();
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

	File file = new File (route);
	marshaller.marshal(p, file);
	marshaller.marshal(p, System.out);

}
	
	
	
public static Patient unmarshal (String route) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File (route);
		Patient p = (Patient) unmarshaller.unmarshal(file);
		
		
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		
		for(Treatment treatment: p.getListOfTreatments()) {
			//We check treatments and prescibers to see if they are already in the database
			Doctor doctor= treatment.getPrescriber();
			if(doctor!=null) {
				if(doctor.getId()!=0) {
				try {
					JPADoctorController.getJPADoctorController().searchDoctorById(doctor.getId());
				}catch(Exception ex) {
					doctor.setId(null);
					JPADoctorController.getJPADoctorController().insertDoctor(doctor);
				}
				}
				else {
					doctor.setId(null);
					JPADoctorController.getJPADoctorController().insertDoctor(doctor);
				}
			}
			
			if(treatment.getId()!=0) {
			try {
				JPATreatmentController.getTreatmentController().searchTreatmentById(treatment.getId());
			}catch(Exception ex) {
				treatment.setId(null);
				JPATreatmentController.getTreatmentController().insertTreatment(treatment);
			}
			}
			else {
				treatment.setId(null);
				JPADoctorController.getJPADoctorController().insertDoctor(doctor);
			}
		}
		
		for (Bills bill: p.getListOfBills()) {
				if(bill.getId()!=0) {
					try {
					JPABillsController.getJPABillsController().searchBillsById(bill.getId());
					}catch(Exception ex) {
						bill.setId(null);
						JPABillsController.getJPABillsController().insertBills(bill);
					}
				}
				else {
					bill.setId(0);
					JPABillsController.getJPABillsController().insertBills(bill);
				}
		}
		
		for (Nurse nurse: p.getListOfNurses()) {
			if(nurse.getId()!=0) {
				try {
					JPANurseController.getNurseController().searchNurseById(nurse.getId());
				}catch(Exception ex) {
					nurse.setId(null);
					JPANurseController.getNurseController().insertNurse(nurse);
				}
			}
			else {
				nurse.setId(null);
				JPANurseController.getNurseController().insertNurse(nurse);
			}
		}
		
		Room room= p.getRoom();
		if(room.getId()!=0) {
			try {
				JPARoomController.getJPARoomController().searchRoomById(room.getId());
			}catch(Exception ex) {
				room.setId(null);
				JPARoomController.getJPARoomController().insertRoom(room);
			}
		}
		
		JPAPatientController.getPatientController().insertCompletePatient(p);
		
		return p;
	}

public static void xml2Html(String sourcePath, String xsltPath, String resultDir) {
	
	TransformerFactory tFactory = TransformerFactory.newInstance();
	try {
		Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
		transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	
	
}