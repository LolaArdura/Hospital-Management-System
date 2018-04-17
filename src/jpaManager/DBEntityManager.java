package jpaManager;
import java.io.*;
import javax.persistence.*;

public class DBEntityManager {
	
	private static EntityManager em = Persistence.createEntityManagerFactory("hospital-management").createEntityManager();
	public static EntityManager getEntityManager() {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		return em;
	}
	
}
