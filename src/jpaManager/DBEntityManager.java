package jpaManager;
import java.io.*;
import java.sql.SQLException;

import javax.persistence.*;

import interfaces.ConnectionInterface;

public class DBEntityManager implements ConnectionInterface{
	
	private static EntityManager em;
	public static EntityManager getEntityManager() {
		if(em==null) {
			em=Persistence.createEntityManagerFactory("hospital-management").createEntityManager();
		}
		return em;
	}
	@Override
	public void stopConnection() throws SQLException {
		em.close();
		
	}
	
}
