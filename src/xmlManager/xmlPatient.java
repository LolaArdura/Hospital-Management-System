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
import model.Patient;
import model.Treatment;
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
	
	private static final String PERSISTENCE_PROVIDER = "hospital-management";	
	private static EntityManagerFactory factory;
	
	
public static Patient unmarshal (String route) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File (route);
		Patient p = (Patient) unmarshaller.unmarshal(file);
		
		
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();
		em.persist(p);
		tx1.commit();
		
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