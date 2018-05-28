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
	
	
public static void marshal (Patient p, File route) throws Exception{
		
	
	EntityManager em=DBEntityManager.getEntityManager();
	
	em = Persistence.createEntityManagerFactory("hospital-management").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
			
	JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
	Marshaller marshaller = jaxbContext.createMarshaller();
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

	marshaller.marshal(p, route);


}
	
	
	
public static Patient unmarshal (File route) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		

		Patient p = (Patient) unmarshaller.unmarshal(route);
		
		
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
					//Doctors should already be in the database
					treatment.setDoctor(null);
				}
				}
				else {
					treatment.setDoctor(null);
				}
			}
			
			if(treatment.getId()!=0) {
			try {
				JPATreatmentController.getTreatmentController().searchTreatmentById(treatment.getId());
			}catch(Exception ex) {
				treatment.setId(null);
				JPATreatmentController.getTreatmentController().insertTreatmentWithoutBill(treatment);
			}
			}
			else {
				treatment.setId(null);
				JPATreatmentController.getTreatmentController().insertTreatmentWithoutBill(treatment);
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
		
		for (int i=0; i<p.getListOfNurses().size();i++ ) {
			if(p.getListOfNurses().get(i).getId()!=0) {
				try {
					JPANurseController.getNurseController().searchNurseById(p.getListOfNurses().get(i).getId());
				}catch(Exception ex) {
					//Nurses should already be in the database
					p.getListOfNurses().remove(i);
				}
			}
			else {
				p.getListOfNurses().remove(i);
			}
		}
		
		Room room= p.getRoom();
		if(room.getId()!=0) {
			try {
				JPARoomController.getJPARoomController().searchRoomById(room.getId());
			}catch(Exception ex) {
				//If the room is not in the hospital, the receptionist will later assign one to the patient
				p.setRoom(null);
			}
		}
		else {
			//If the room is not in the hospital, the receptionist will later assign one to the patient
			p.setRoom(null);
		}
		
		JPAPatientController.getPatientController().insertCompletePatient(p);
		
		return p;
	}

public static void xml2Html(String sourcePath, String resultDir) {
	
	TransformerFactory tFactory = TransformerFactory.newInstance();
	try {
		Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Patient-StyleSheet.xslt")));
		transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	
	
}