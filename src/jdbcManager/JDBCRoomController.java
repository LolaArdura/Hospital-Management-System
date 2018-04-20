package jdbcManager;
import model.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import interfaces.*;
public class JDBCRoomController implements RoomInterface {

	private static JDBCRoomController singleton;

	public static  JDBCRoomController getRoomController() {
		if (singleton == null) {
			singleton = new JDBCRoomController();

		}
		return singleton;

	}

	public boolean insertRoom(Room room) throws Exception {
		String sql = "INSERT INTO room ( number, type, capacity, floor, costPerDay)" + " VALUES (?,?,?,?,?);";

		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getNumber());
		prep.setString(2, room.getType().name().toLowerCase());
		prep.setInt(3, room.getCapacity());
		prep.setInt(4, room.getFloor());
		prep.setFloat(5, room.getCostPerDay());
		prep.executeUpdate();
		prep.close();
		return true;

	}
	
	public boolean deleteRoom(Room room) throws Exception {
		String sql = "DELETE FROM room WHERE id = ?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getId());
		prep.executeUpdate();
		return true;
	}

	public Room searchRoomById(Integer id) throws Exception {
		String sql = "SELECT * FROM room WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		rs.next();
		int Id = rs.getInt("id");
		int number = rs.getInt("number");
		Room.roomType type = Room.roomType.valueOf(rs.getString("type").toUpperCase());
		int capacity = rs.getInt("capacity");
		int floor = rs.getInt("floor");
		float costPerDay = rs.getFloat("costPerDay");
		Room room = new Room(Id, number, type, floor, capacity, costPerDay);
		return room;
	}

	public void updateRoom(Room room) throws Exception {
		String sql = "UPDATE room SET number=?, type=?, capacity=?, floor=?, costPerDay=? WHERE id=?";
		PreparedStatement prep = JDBConnection.getConnection().prepareStatement(sql);
		prep.setInt(1, room.getNumber());
		prep.setString(2, room.getType().name().toUpperCase());
		prep.setInt(3, room.getCapacity());
		prep.setInt(4, room.getFloor());
		prep.setFloat(5, room.getCostPerDay());
		prep.setInt(6, room.getId());
		prep.executeUpdate();
	}
	
	public List<Room> getAllRooms() throws Exception {
		Statement stmt = JDBConnection.getConnection().createStatement();
		String sql = "SELECT * FROM room";
		ResultSet rs = stmt.executeQuery(sql);
		List<Room> roomList = new LinkedList<Room>();
		while (rs.next()) {
			int Id = rs.getInt("id");
			int number = rs.getInt("number");
			Room.roomType type = Room.roomType.valueOf(rs.getString("type").toUpperCase());
			int floor = rs.getInt("floor");
			int capacity = rs.getInt("capacity");
			float costPerDay = rs.getFloat("costPerDay");
			Room searchRoom = new Room(Id, number, type, floor, capacity, costPerDay);
			roomList.add(searchRoom);
		}
		stmt.close();
		return roomList;
	}
	
	
}
