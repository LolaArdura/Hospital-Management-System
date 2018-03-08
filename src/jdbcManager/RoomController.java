package jdbcManager;
 
import java.sql.*;

import model.Room;

public class RoomController implements RoomInterface {
   
	private static RoomController singleton;
	public RoomController getRoomController() {
		if (singleton == null) {
			singleton = new RoomController (); 
			
		}
		return singleton;
		
	}
	
	public boolean insertRoom (Room room) throws Exception {
		String sql = "INSERT INTO room (id, number, type, capacity, floor, costPerDay)" 
	   + " VALUES (?,?,?,?,?,?,?);";
		
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getId());
		prep.setInt(2, room.getNumber());
<<<<<<< HEAD
		prep.setString(3, room.getType().name());
=======
		prep.setString(3, room.getType().name().toLowerCase());
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System.git
		prep.setInt(4, room.getCapacity());
		prep.setInt(5, room.getFloor());
		prep.setFloat(6, room.getCostPerDay());	
		prep.executeUpdate();
		prep.close();
		return true;

	}
	
	public boolean deleteRoom (Room room ) throws Exception {
		String sql = "DELETE FROM room WHERE id = ?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getId());
		prep.executeUpdate();
		return true;
	}
	
	public Room searchRoomById (Integer id) throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT FROM room WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int Id = rs.getInt("id");
			int number = rs.getInt("number");
			Room.roomType type = Room.roomType.valueOf(rs.getString("type").toUpperCase());
			int capacity = rs.getInt("capacity");
			int floor = rs.getInt("floor");
			float costPerDay = rs.getFloat("costPerDay");
			Room room = new Room (Id, number, type, floor, capacity, costPerDay) ;
			return room;
		}
		stmt.close();
		return null;
	}
	
	public Room updateRoom (Room room) throws Exception{
		String sql = "UPDATE * FROM room WHERE id=?";
		PreparedStatement prep = DBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getId());
		prep.executeUpdate();
		return room;
	}
}
