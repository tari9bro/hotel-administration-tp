package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.dao.AdminDAO;
import model.dao.ClientDAO;
import model.dao.ConnectionManager;
import model.dao.ReservationDAO;
import model.dao.RoomDAO;
import model.entities.Admin;
import model.entities.Client;
import model.entities.Reservation;
import model.entities.Room;

public class DashboardC {
	
	private ClientDAO clientDAO;
	private AdminDAO adminDAO;
	private RoomDAO roomDAO;
	private ReservationDAO reservationDAO;
    Connection connection ;
	
	public DashboardC() {
		super();
		
		try {
			connection = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adminDAO = new AdminDAO(connection);
		clientDAO = new ClientDAO(connection);
		roomDAO = new RoomDAO(connection);
		reservationDAO = new ReservationDAO(connection);
	}
	//Admin crud implementation
	public String createAdmin(String adminName, String adminPass) {
		
		if(!adminName.isEmpty() || !adminPass.isEmpty()) {
			if(adminDAO.get(adminName) == null) {
				Admin admin = new Admin(adminName,adminPass);
				if(adminDAO.add(admin)) {
					return "admin added successfully";
				}else {
					return "admin couldn't be added";
				}
			}else {
				return "admin already exists";
				
			}
			
			
		}else {
			if(adminName.isEmpty() && adminPass.isEmpty()) {
				return "add admin name and password";
			}else if(adminName.isEmpty() && !adminPass.isEmpty()) {
				
				return "add admin name";
			} else if(!adminName.isEmpty() && adminPass.isEmpty()) {
				return "add admin pass";
			}
		}
		
		return null;
	}
	public Admin readAdmin(Integer adminId) {
		
		if(adminDAO.get(adminId)!= null) {
			return adminDAO.get(adminId);
		}else {
		return null;
		}
	}
	public String updateAdmin(Integer id, String name, String pass) {
		
		
		Admin admin = new Admin();
		
		
			admin.setId(id);
			admin.setName(name);
			admin.setPass(pass);
			
			if(adminDAO.get(id)!= null) {
				
				if(adminDAO.update(admin)) {
					return "admin updated successfully";
				}else {
					return "admin couldn't be  updated ";
				}
			}else {
			return "admin doesn't exists ";
			}
		
		
	
		
	}
	
	public String deleteAdmin(Integer id) {
		
		if(adminDAO.get(id)!= null) {
			if(adminDAO.delete(id)) {
				return "admin deleted successfully";
			}else {
				return "admin wasen't deleted ";
			}
		
		}else {
			return "admin doesn't exists ";
		}
		
		}
	
	//Client crud implementation
	public String createClient(String clientName, String clientPass) {
		
		if(!clientName.isEmpty() && !clientPass.isEmpty()) {
			Client client = new Client(clientName,clientPass);
			if(clientDAO.add(client)) {
				return "client creted successfully";
			}else {
				return "client wasen't creted ";
			}
			
		}else if (clientName.isEmpty() && clientPass.isEmpty()){
			return "please fill name and pass fields ";
		}
		else {
			if (clientName.isEmpty()) {
				return "name is empty";
			}else {
				return "pass is empty ";
			}
			
				
			}
		}
	
	public Client readClient(Integer clientId) {
		
		if(clientDAO.get(clientId)!= null) {
			return clientDAO.get(clientId);
		}else {
		return null;
		}
	}
	public String updateClient(Integer id, String name, String pass) {
		
		
		Client client = new Client();
		
		
		client.setId(id);
		client.setName(name);
		client.setPass(pass);
			
			if(adminDAO.get(client.getId())!= null) {
				
				if(clientDAO.update(client)) {
					return "client updated successfully";
				}else {
					return "client couldn't be  updated ";
				}
			}else {
			return "client doesn't exists ";
			}
		
		
	
		
	}
	public String deleteClient(Integer id) {
		
		if(clientDAO.get(id)!= null) {
			if(clientDAO.delete(id)) {
				return "client deleted successfully";
			}else {
				return "client wasen't deleted ";
			}
		
		}else {
			return "client doesn't exists ";
		}
		
		}
		
		
		//Room crud implementation
	public String createRoom(Integer roomId) {
		
			if(roomDAO.get(roomId) == null) {
				Room room = new Room(roomId);
				if(roomDAO.add(room)) {
					return "room added successfully";
				}else {
					return "room couldn't be added";
				}
			}else {
				return "room already exists";
				
			}
			}
			
	public Room readRoom(Integer roomId) {
		
		if(roomDAO.get(roomId)!= null) {
			return roomDAO.get(roomId);
		}else {
		return null;
		}
	}
	
	//for admins 
	public String  UpdateRoom( Integer reservationDuration,int userid) throws SQLException {
		
		Room room = new Room();
		
		
		
		room.setReservationDuration(reservationDuration);
		
		
		
			
			if(roomDAO.update(userid,room)) {
				return "room reserved successfully";
			}else {
				return "room already reserved ";
			}
		
	}
	public String deleteRoom(Integer id) {
		
		if(roomDAO.get(id)!= null) {
			if(roomDAO.delete(id)) {
				return "room deleted successfully";
			}else {
				return "room wasen't deleted ";
			}
		
		}else {
			return "room doesn't exists ";
		}
		
		}
	public boolean addReservation(int clientID, int roomID, String status) {
        Reservation reservation = new Reservation(clientID, roomID, status);
        return reservationDAO.add(reservation);
    }

	
	
	public String requestReservation(int clientID,  String status) {
        Reservation reservation = reservationDAO.findByClientId(clientID) ;
        if(reservation == null) {
        	reservation = new Reservation(clientID,status);
        	reservation.setStatus(status);
        	reservation.setClientID(clientID);
        	reservationDAO.add(reservation);
        	return "request recived sussessfully and it will be handled by an admin";
        }else {
        	return "you already reserved a room";
        }
        
        
       
    }
	
	
    public String updateReservation( int clientId, int duration)  {
    	
        Reservation reservation = reservationDAO.findByClientId(clientId) ;
        if(reservation.getRoomID() != null) {
        	try {
        	Room room = roomDAO.get(clientId);
        	room.setReservationDuration(duration);
        	roomDAO.update(clientId, room);
        	return "updated sussessfully";} catch (Exception e) {
                e.printStackTrace();
                return "An error occurred while updating reservation.";
            }
        }else {
        	return "you didn't reserve a room";
        }
         
    }

    public boolean cancelReservation(int requestID) {
        return reservationDAO.delete(requestID);
    }

    public Reservation findReservationById(int requestId) {
        return reservationDAO.findById(requestId);
    }
    public Reservation findReservationByClientId(int clientID) {
        return reservationDAO.findByClientId(clientID);
    }
    public List<Reservation> findAllReservations() {
        return reservationDAO.findAll();
    }
	

        public String getReservation(int clientId) {
        	
            Reservation reservation = findReservationByClientId(clientId);
            StringBuilder stringBuilder = new StringBuilder();
            if (reservation == null) {
                stringBuilder.append("No reservation found for client ID ").append(clientId);
            } else {
                stringBuilder.append("Reservation ID: ").append(reservation.getRequestID())
                             .append(", Room ID: ").append(reservation.getRoomID())
                             .append(", Status: ").append(reservation.getStatus());
            }

            return stringBuilder.toString();
        }
		public String acceptReservation(int id, int duration, Object object) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String acceptReservation(int clientId, int roomId, int requestId) {
	       
	        Reservation reservation = reservationDAO.findById(requestId);

	       
	        if (reservation != null && reservation.getClientID() == clientId) {
	            try {
	               
	                reservation.setRoomID(roomId);
                   System.out.println("ttt"+reservation.getRoomID()+reservation.getClientID()+reservation.getRequestID());
	                
	                if (reservationDAO.acceptReservation(reservation)) {
	                    return "Reservation accepted successfully.";
	                } else {
	                    return "Failed to accept reservation. Please try again later.";
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                return "An error occurred while accepting reservation.";
	            }
	        } else {
	            return "Invalid request. The reservation may not exist or does not belong to the specified client.";
	        }
	    }
    }



