package jpaManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import interfaces.*;
import model.Room;

public class JPARoomController implements  RoomInterface{
	
	private static JPARoomController singleton;

	public static  JPARoomController getJPARoomController() {
		if (singleton == null) {
			singleton = new JPARoomController();

		}
		return singleton;
	}
	public boolean insertRoom(Room room) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(room);
		em.getTransaction().commit();
		return true;
	}
	
	public boolean deleteRoom(Room room) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(room);
		em.getTransaction().commit();
		return true;
	}
	
	public Room searchRoomById(Integer id) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM room WHERE id LIKE ?", Room.class);
		q1.setParameter(1, id);
		Room room = (Room) q1.getSingleResult();
		return room;
	}
	
	public List<Room> getAllRooms() throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM room", Room.class);
		List<Room> rooms = (List<Room>) q1.getResultList();
		return rooms;
	}
	
	public void updateRoom (Room room) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
	}
}
