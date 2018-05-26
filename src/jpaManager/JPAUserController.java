package jpaManager;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.UserInterface;
import model.User;

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
		try {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<User> getAllUsers() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.flush();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user", User.class);
		List <User> users = (List<User>) q1.getResultList();
		return users;
		}catch(Exception e) {
			e.printStackTrace();
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public List<User> searchUserByType(String type) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user WHERE type = ?", User.class);
		q1.setParameter(1, type);
		List<User> users = (List<User>) q1.getResultList();
		return users;
		}catch(Exception e) {
			e.printStackTrace();
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public User validateUser(User user) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT id, type FROM user WHERE username = ? and password = ?", User.class);
		q1.setParameter(1, user.getUsername());
		q1.setParameter(2, user.getPassword());
		User u = (User) q1.getSingleResult();
		return u;
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public void deleteUser(User user) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		User userReceived = JPAUserController.getJPAUserController().searchUserById(user.getId());
		em.getTransaction().begin();
		em.remove(userReceived);
		em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public User searchUserById(Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user WHERE id LIKE ?", User.class);
		q1.setParameter(1, id);
		User user = (User) q1.getSingleResult();
		return user;
		}catch(Exception e) {
			e.printStackTrace();
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
	
	public void updateUser(User user) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();
		try {
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		}catch(Exception e) {
			 em.getTransaction().commit();
			 throw new Exception();
		 }
	}
}
