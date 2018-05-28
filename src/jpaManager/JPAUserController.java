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
	
	public List<User> searchUserByType(String type) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user WHERE type = ?", User.class);
		q1.setParameter(1, type);
		List<User> users = (List<User>) q1.getResultList();
		return users;
		
	}
	
	public User validateUser(User user) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT id, type FROM user WHERE username = ? and password = ?", User.class);
		q1.setParameter(1, user.getUsername());
		q1.setParameter(2, user.getPassword());
		User u = (User) q1.getSingleResult();
		return u;
		
	}
	
	public void deleteUser(User user) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();

		User userReceived = JPAUserController.getJPAUserController().searchUserById(user.getId());
		em.getTransaction().begin();
		em.remove(userReceived);
		em.getTransaction().commit();
		
	}
	
	public User searchUserById(Integer id) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM user WHERE id LIKE ?", User.class);
		q1.setParameter(1, id);
		User user = (User) q1.getSingleResult();
		return user;
		
	}
	
	public void updateUser(User user) throws Exception {
		EntityManager em=DBEntityManager.getEntityManager();

		em.getTransaction().begin();
			user.setUsername(user.getUsername());
			user.setPassword(user.getPassword());
		em.getTransaction().commit();
		
	}
}
