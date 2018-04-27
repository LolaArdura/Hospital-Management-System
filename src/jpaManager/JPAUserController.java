package jpaManager;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.UserInterface;
import model.User;
import model.User.userType;

public class JPAUserController implements UserInterface{
	private static JPAUserController singleton;
	
	private JPAUserController() {
		super();
	}
	
	public static JPAUserController getJPAUserController() {
		if(singleton ==null) {
			singleton=new JPAUserController();
		}
		return singleton;
	}
	
	public void insertUser(User user) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public List<User> getAllUsers() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.flush();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user", User.class);
		List <User> users = (List<User>) q1.getResultList();
		return users;
	}
	
	public List<User> searchUserByType(userType usertype){
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createQuery("SELECT * FROM user WHERE usertype LIKE ?", User.class);
		q1.setParameter(1, usertype);
		LinkedList<User> users = (LinkedList<User>) q1.getResultList();
		return users;
	}
}
