package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.entities.Reservation;
import model.entities.Room;

public class RoomDAO {

    private Connection connection;
    private ReservationDAO reservationDAO;
    public RoomDAO(Connection connection) {
        this.connection = connection;
        reservationDAO = new ReservationDAO(connection);
    }
    

    public boolean add(Room room) {
        try {
            String sql = "INSERT INTO rooms (room_id) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, room.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean update(int userd,Room room) throws SQLException {
    	Reservation reservaion = reservationDAO.findByClientId(userd);
    	
    	if(reservaion.getRoomID() != 0) {
    		
    		  String sql = "UPDATE rooms SET  reservation_duration = ? WHERE room_id = ?";
              PreparedStatement statement = connection.prepareStatement(sql);
              
              statement.setInt(1, room.getReservationDuration());
              statement.setInt(2, room.getId());
              int rowsUpdated = statement.executeUpdate();
              return rowsUpdated > 0;
          }
    	else {
                return false;
            }
        
    }


    public boolean delete(int roomId) {
        try {
            String sql = "DELETE FROM rooms WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Room get(int roomId) {
        try {
            String sql = "SELECT * FROM rooms WHERE room_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int reservationDuration = resultSet.getInt("reservation_duration");
                return new Room(roomId,  reservationDuration);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error while getting room with roomId: " + roomId, ex);
        }
    }

    
    public int updateReservation(int userId) {
    	int result = 0;
    	try {
	        String sql = "UPDATE rooms SET reserved = NULL WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setInt(1, userId);
	        result = statement.executeUpdate();
	        
	       
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	         
	    }
    	return result;
    }
    
    public Map<Integer, Room> getAll() throws SQLException {
        Map<Integer, Room> roomMap = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                
                
                int reservationDuration = resultSet.getInt("reservation_duration");
                Room room = new Room(roomId, reservationDuration);
                roomMap.put(roomId, room);
            }
        }

        return roomMap;
    }
}
