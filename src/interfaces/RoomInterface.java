package interfaces;
import model.Room;

import java.util.*;


public interface RoomInterface {
   public boolean insertRoom (Room room) throws Exception;
   public boolean deleteRoom (Room room) throws Exception;
   public Room searchRoomById (Integer id) throws Exception;
   public Room updateRoom (Room room) throws Exception;
   public List <Room> getAllRooms () throws Exception ;
}
