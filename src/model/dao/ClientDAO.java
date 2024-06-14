package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

import model.entities.Client;

public class ClientDAO  {

 	
	 private Connection connection;

	    public ClientDAO(Connection connection) {
	        this.connection = connection;
	    }

	
	   


		public boolean add(Client a) {
	        try {
	            String sql = "INSERT INTO Clients (name, pass) VALUES (?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, a.getName());
	            statement.setString(2, a.getPass());
	            statement.executeUpdate();
	            return true;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    public boolean update(Client a) {
	        try {
	            String sql = "UPDATE Clients SET name = ?, pass = ? WHERE id = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, a.getName());
	            statement.setString(2, a.getPass());
	            statement.setInt(3, a.getId());
	            int rowsUpdated = statement.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    public boolean delete(int ClientId) {
	        try {
	            String sql = "DELETE FROM Clients WHERE id = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setInt(1, ClientId);
	            int rowsDeleted = statement.executeUpdate();
	            return rowsDeleted > 0;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    public Client get(int ClientId) {
	        try {
	            String sql = "SELECT * FROM Clients WHERE id = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setInt(1, ClientId);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String pass = resultSet.getString("pass");
	                return new Client(ClientId, name, pass);
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Error while getting user with userId: " + ClientId, ex);
	        }
	    }

	    public Client get(String ClientName) {
	        try {
	            String sql = "SELECT * FROM Clients WHERE name = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, ClientName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String pass = resultSet.getString("pass");
	                return new Client(id, ClientName, pass);
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Error while getting user with username: " + ClientName, ex);
	        }
	    }

	
	 public Map<String, String> getAll() throws SQLException {
	        Map<String, String> ClientMap = new HashMap<>();

	        try (PreparedStatement statement = connection.prepareStatement("SELECT name, pass FROM Clients");
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String pass = resultSet.getString("pass");
	                ClientMap.put(name, pass);
	            }
	        }

	        return ClientMap;
	    }
	 
	 
	
	 
	
	 
} 