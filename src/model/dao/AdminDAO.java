package model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

import model.entities.Admin;






 public  class AdminDAO   {

 	
	 private Connection connection;

	    public AdminDAO(Connection connection) {
	        this.connection = connection;
	    }

	
	   


		public boolean add(Admin a) {
	        try {
	            String sql = "INSERT INTO admins (name, pass) VALUES (?, ?)";
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

	    public boolean update(Admin a) {
	        try {
	            String sql = "UPDATE admins SET name = ?, pass = ? WHERE id = ?";
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

	    public boolean delete(int adminId) {
	        try {
	            String sql = "DELETE FROM admins WHERE id = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setInt(1, adminId);
	            int rowsDeleted = statement.executeUpdate();
	            return rowsDeleted > 0;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }

	    public Admin get(int adminId) {
	        try {
	            String sql = "SELECT * FROM admins WHERE id = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setInt(1, adminId);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String pass = resultSet.getString("pass");
	                return new Admin(adminId, name, pass);
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Error while getting user with userId: " + adminId, ex);
	        }
	    }

	    public Admin get(String adminName) {
	        try {
	            String sql = "SELECT * FROM admins WHERE name = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, adminName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String pass = resultSet.getString("pass");
	                return new Admin(id, adminName, pass);
	            } else {
	                return null;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Error while getting user with username: " + adminName, ex);
	        }
	    }

	
	 public Map<String, String> getAll() throws SQLException {
	        Map<String, String> adminMap = new HashMap<>();

	        try (PreparedStatement statement = connection.prepareStatement("SELECT name, pass FROM admins");
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String pass = resultSet.getString("pass");
	                adminMap.put(name, pass);
	            }
	        }

	        return adminMap;
	    }

 } 