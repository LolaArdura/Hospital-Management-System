package jdbcManager;
import model.Room;

public interface RoomInterface {
   public boolean insertRoom (Room room) throws Exception;
   public boolean deleteRoom (Room room) throws Exception;
   public Room searchRoom (Integer id) throws Exception;
   public Room updateRoom (Room room);
}
