package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.dao.AdminDAO;
import model.dao.ClientDAO;
import model.dao.ConnectionManager;

public class LoginC {
	private Map<String, String> admins; 
    private Map<String, String> clients;
    private AdminDAO adminDAO;
    private ClientDAO clientDAO;
    Connection connection ;
	
	public LoginC() {
		super();
		try {
			connection = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adminDAO = new AdminDAO(connection);
    	admins = new HashMap<>(); 
    	clientDAO = new ClientDAO(connection);
    	clients = new HashMap<>();
    	try {
    		clients.putAll(clientDAO.getAll());
    	    admins.putAll(adminDAO.getAll());
    	} catch (Exception e) {
    	    
    	    e.printStackTrace(); 
    	}
	}
    

	
	 public String loginToApp(String username, String password) {
	        if (admins.containsKey(username)) {
	            if (admins.get(username).equals(password)) {
	                
	                return "admin login succeeded.";
	            } else {
	                return "Password not correct.";
	            }
	        }
	        else if (clients.containsKey(username)) {
	            if (clients.get(username).equals(password)) {
	           
	            	return "client login succeeded."+clientDAO.get(username).getId();
	                
	            } else {
	            	return "Password not correct.";
	            }
	        }
	        else {
	        	return "Username not found.";
	        }
	       
	    }
}
