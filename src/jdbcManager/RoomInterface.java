package jdbcManager;
import model.Room;
import model.Patient;

public interface RoomInterface {
   public boolean insertRoom (Room room) throws Exception;
   public boolean deleteRoom (Room room) throws Exception;
   public Room searchRoomById (Integer id) throws Exception;
   public boolean updateRoom (Room room) throws Exception;
  
}
