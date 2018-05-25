package interfaces;
import model.Room;

import java.util.*;


public interface RoomInterface {
   public boolean insertRoom (Room room) throws Exception;
   public boolean deleteRoom (Room room) throws Exception;
   public Room searchRoomById (Integer id) throws Exception;
   public void updateRoom (Room room) throws Exception;
   public List <Room> getAllRooms () throws Exception ;
   public float searchCost(String type) throws Exception;
   public List<Room> getRoomsByType(String type) throws Exception;
   public List<Room> getRoomsAndCosts() throws Exception;
   public List<Room> getFreeRooms() throws Exception;
   public void updateCost(Float cost,String type) throws Exception;
}
