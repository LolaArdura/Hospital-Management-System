package jdbcManager;
 
import java.sql.*;

import model.Nurse;
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
		prep.setString(3, room.getType().name());
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
	
	public Room searchRoom (Integer id) throws Exception {
		Statement stmt = DBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM room";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int Id = rs.getInt("id");
			int number = rs.getInt("number");
			//METER LA ENUMERACION
			int capacity = rs.getInt("capacity");
			int floor = rs.getInt("floor");
			float costPerDay = rs.getFloat("costPerDay");
			Room room = new Room ();
			return room;
		}
		rs.close();
		stmt.close();
		return null;
	}
	
	public Room updateBills (Room room) {
		
		
	}
	
	
}
