package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.entities.Reservation;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Reservation reservation) {
        try {
            String sql = "INSERT INTO Reservation (ClientID, RoomID, Status) VALUES (?, DEFAULT, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservation.getClientID());
            statement.setString(2, reservation.getStatus());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean acceptReservation(Reservation reservation) {
        try {
            
            String sql = "UPDATE Reservation SET RoomID = ?, Status = ? WHERE RequestID = ? AND ClientID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
           
            statement.setInt(1, reservation.getRoomID());
            statement.setString(2, "reservation accepted");
            statement.setInt(3, reservation.getRequestID());
            statement.setInt(4, reservation.getClientID());
            
           
            int rowsUpdated = statement.executeUpdate();
            
           
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return false; 
        }
    }

    public boolean update(Reservation reservation) {
        try {
            String sql = "UPDATE Reservation SET ClientID = COALESCE(?, ClientID), RoomID = COALESCE(?, RoomID), Status = COALESCE(?, Status) WHERE RequestID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            
           
            if (reservation.getClientID() != null) {
                statement.setInt(1, reservation.getClientID());
            } else {
                statement.setNull(1, Types.INTEGER); 
            }
            
            if (reservation.getRoomID() != null) {
                statement.setInt(2, reservation.getRoomID());
            } else {
                statement.setNull(2, Types.INTEGER); 
            }
            
            if (reservation.getStatus() != null) {
                statement.setString(3, reservation.getStatus());
            } else {
                statement.setNull(3, Types.VARCHAR); 
            }
            
            statement.setInt(4, reservation.getRequestID()); 
            
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public boolean delete(int requestID) {
        try {
            String sql = "DELETE FROM Reservation WHERE requestid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, requestID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Reservation findByClientId(int clientId) {
        try {
            String sql = "SELECT * FROM reservation WHERE clientid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int requestId = resultSet.getInt("requestid");
                int roomID = resultSet.getInt("roomid");
                String status = resultSet.getString("status");
                return new Reservation( roomID, status,requestId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Reservation findById(int requestId) {
        try {
            String sql = "SELECT * FROM Reservation WHERE RequestID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, requestId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int clientID = resultSet.getInt("ClientID");
                int roomID = resultSet.getInt("RoomID");
                String status = resultSet.getString("Status");
                return new Reservation(requestId, clientID, roomID, status); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Reservation";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int requestID = resultSet.getInt("RequestID");
                int clientID = resultSet.getInt("ClientID");
                int roomID = resultSet.getInt("RoomID");
                String status = resultSet.getString("Status");
                reservations.add(new Reservation(requestID, clientID, roomID, status));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reservations;
    }

	
}


