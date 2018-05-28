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
	
			Room roomReceived= JPARoomController.getJPARoomController().searchRoomById(room.getId());
		em.getTransaction().begin();
		em.remove(roomReceived);
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
		    room.setFloor(room.getFloor());
			room.setNumber(room.getNumber());
			room.setCapacity(room.getCapacity());
			room.setType(room.getType());
			room.setCostPerDay(room.getCostPerDay());
		em.getTransaction().commit();
		
	}
	
	public float searchCost(String type) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT costPerDay FROM room GROUP BY type HAVING type =?", Room.class);
		q1.setParameter(1, type);
		Float cost = (float) q1.getSingleResult();
		return cost;
		
	}
	
	public List<Room> getRoomsByType(String type) throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();

		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT * FROM room WHERE type=?", Room.class);
		q1.setParameter(1,  type);
		List<Room> rooms = (List<Room>) q1.getResultList();
		return rooms;
		
	}
	
	public List<Room> getRoomsAndCosts() throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
		
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT type, costPerDay FROM room GROUP BY type",Room.class);
		List<Room> rooms = (List<Room>) q1.getResultList();
		return rooms;
		
	}
	
	public List<Room> getFreeRooms() throws Exception {
		EntityManager em = DBEntityManager.getEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		Query q1 = em.createNativeQuery("SELECT room.id, number, floor, type, costPerDay FROM room LEFT JOIN patient ON "
				+ "room.id=patient.room_id GROUP BY room.id HAVING COUNT(patient.id)<capacity", Room.class);
		List<Room> rooms = (List<Room>) q1.getResultList();
		return rooms;
		
	}
	
	public void updateCost(Float cost, String type) throws Exception{
		EntityManager em = DBEntityManager.getEntityManager();
	
		em.getTransaction().begin();
		List<Room> rooms = JPARoomController.getJPARoomController().getRoomsByType(type);
		for(Room r:rooms) {
			r.setCostPerDay(cost);
		}
		em.getTransaction().commit();
		
	}
	

}
