package xmlManager;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import jdbcManager.JDBCBillsController;
import jdbcManager.JDBCNurseController;
import jdbcManager.JDBCPatientController;
import jdbcManager.JDBCTreatmentController;
import jdbcManager.JDBConnection;
import jpaManager.DBEntityManager;
import model.Doctor;
import model.Patient;
import model.Room;
import model.Treatment;
import model.Bills;
import model.Nurse;
import jpaManager.*;


public class XmlPatient {
	
	
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
		
		List<Treatment> treatments= p.getListOfTreatments();
		List<Bills> bills= p.getListOfBills();
		List<Nurse> nurses= p.getListOfNurses();
		
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
		
		JDBCPatientController.getPatientController().insertNoRoomPatient(p);
		Connection c= JDBConnection.getConnection();
		String query = "SELECT last_insert_rowid() AS lastId";
		PreparedStatement prep = c.prepareStatement(query);
		ResultSet rs = prep.executeQuery();
		Integer lastId = rs.getInt("lastId");
		rs.close();
		prep.close();
		
		p.setId(lastId);
		
		for(Treatment treatment: treatments) {
			//We check treatments and prescibers to see if they are already in the database: we assume that
			//prescribers are doctor from the hospital, therefore, they exist in the database
			
			
			if(treatment.getId()!=0) {
			try {
				JPATreatmentController.getTreatmentController().searchTreatmentById(treatment.getId());
			}catch(Exception ex) {
				treatment.setId(null);
				treatment.setPatient(p);
				JDBCTreatmentController.getTreatmentController().insertTreatmentWithoutBill(treatment);
			}
			}
			else {
				treatment.setId(null);
				treatment.setPatient(p);
				JDBCTreatmentController.getTreatmentController().insertTreatmentWithoutBill(treatment);
			}
		}
		
		
		for (Bills bill: bills) {
				if(bill.getId()!=0) {
					try {
					JPABillsController.getJPABillsController().searchBillsById(bill.getId());
					}catch(Exception ex) {
						bill.setId(null);
						bill.setPatient(p);
						JDBCBillsController.getBillsController().insertBills(bill);
					}
				}
				else {
					bill.setId(0);
					bill.setPatient(p);
					JDBCBillsController.getBillsController().insertBills(bill);
				}
		}
		
		//We are going to assume that if the patient has nurses assigned, those nurses are already in the database
		
		
		
		//We assign the bills and the treatments to the patient
		
		for(Nurse n: nurses) {
			JDBCNurseController.getNurseController().addPatientToNurse(n, p);
		}
		
		
		return p;
	}

public static void xml2Html(File sourcePath, File resultDir) {
	
	TransformerFactory tFactory = TransformerFactory.newInstance();
	try {
		Transformer transformer = tFactory.newTransformer(new StreamSource(new File("./xmls/Patient-StyleSheet.xslt")));
		transformer.transform(new StreamSource(sourcePath),new StreamResult(resultDir));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
	
	
	
}