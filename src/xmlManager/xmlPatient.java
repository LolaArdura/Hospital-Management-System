package xmlManager;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jpaManager.DBEntityManager;
import model.Patient;
import sample.db.pojos.Report;
import jpaManager.*;


public class xmlPatient {
	
	public static void marshal () throws Exception{
		
	
	EntityManager em=DBEntityManager.getEntityManager();
	
	em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
			
	JAXBContext jaxbContext = JAXBContext.newInstance(PatientsList.class);
	Marshaller marshaller = jaxbContext.createMarshaller();
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

	PatientsList pt = new PatientsList(jpaManager.JPAPatientController.getPatientController().getAllPatients());
	File file = new File ("./xmls/Sample-Patients.xml");
	marshaller.marshal(pt, file);
	marshaller.marshal(pt, System.out);

}
	
	public static void unmarshal () throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(PatientsList.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		
		
	}
	
	
	
}