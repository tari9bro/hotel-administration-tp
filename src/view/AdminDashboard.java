package view;

import javax.swing.*;

import controller.DashboardC;
import model.entities.Admin;
import model.entities.Client;
import model.entities.Reservation;
import model.entities.Room;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

  
	private static final long serialVersionUID = 1L;

	public AdminDashboard(String adminName) {
        setTitle("Admin Dashboard");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menu items
        JMenu adminMenu = new JMenu("Admin");
        JMenuItem createAdminItem = new JMenuItem("Create");
        JMenuItem readAdminItem = new JMenuItem("Read");
        JMenuItem updateAdminItem = new JMenuItem("Update");
        JMenuItem deleteAdminItem = new JMenuItem("Delete");

        // Add menu items to admin menu
        adminMenu.add(createAdminItem);
        adminMenu.add(readAdminItem);
        adminMenu.add(updateAdminItem);
        adminMenu.add(deleteAdminItem);

        // Create client menu
        JMenu clientMenu = new JMenu("Client");
        JMenuItem createClientItem = new JMenuItem("Create");
        JMenuItem readClientItem = new JMenuItem("Read");
        JMenuItem updateClientItem = new JMenuItem("Update");
        JMenuItem deleteClientItem = new JMenuItem("Delete");

        // Add menu items to client menu
        clientMenu.add(createClientItem);
        clientMenu.add(readClientItem);
        clientMenu.add(updateClientItem);
        clientMenu.add(deleteClientItem);

        // Create room menu
        JMenu roomMenu = new JMenu("Room");
        JMenuItem createRoomItem = new JMenuItem("Create");
        JMenuItem readRoomItem = new JMenuItem("Read");
        JMenuItem updateRoomItem = new JMenuItem("Update");
        JMenuItem deleteRoomItem = new JMenuItem("Delete");
        

        // Add menu items to room menu
        roomMenu.add(createRoomItem);
        roomMenu.add(readRoomItem);
        roomMenu.add(updateRoomItem);
        roomMenu.add(deleteRoomItem);
        

        // Add menus to menu bar
        menuBar.add(adminMenu);
        menuBar.add(clientMenu);
        menuBar.add(roomMenu);

        setJMenuBar(menuBar);

        // Create main area
        JPanel mainPanel = new JPanel();
        add(mainPanel, BorderLayout.CENTER);

        // Create welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + adminName + "!");
        mainPanel.add(welcomeLabel);

        
        //create DashboardC object 
        DashboardC dashboardC = new DashboardC();
        // Add action listeners for menu items
        ActionListener menuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem source = (JMenuItem) e.getSource();
                String menuItemText = source.getText();
                // Handle menu item clicks
                

                // Get the parent of the JMenuItem, which is the JPopupMenu
                JPopupMenu popupMenu = (JPopupMenu) source.getParent();
                
                // Get the invoker of the popup menu, which should be the JMenu
                Component invoker = popupMenu.getInvoker();
                JMenu parentMenu = (JMenu) invoker;
                // Get the name of the menu containing the menu item
                String menuName = parentMenu.getText();
                
                
                if(menuName.equals("Admin")) {
                	
                	switch(menuItemText) {
                	  case "Create":
                		  createAdmin(mainPanel,dashboardC);
                	    break;
                	  case "Read":
                		  readAdmin(mainPanel,dashboardC);
                	    break;
                	  case "Update":
                		  updateAdmin(mainPanel,dashboardC);
                  	    break;
                	  case "Delete":
                		  deleteAdmin(mainPanel,dashboardC);
                  	    break;
                	  default:
                	    // code block
                	}
                	
                }
                else if (menuName.equals("Client")) {
                	
                	switch(menuItemText) {
              	  case "Create":
              		createClient(mainPanel,dashboardC);
              	    break;
              	  case "Read":
              		readClient(mainPanel,dashboardC);
              	    break;
              	  case "Update":
              		updateClient(mainPanel,dashboardC);
                	    break;
              	  case "Delete":
              		deleteClient(mainPanel,dashboardC);
                	    break;
              	  default:
              	    // code block
              	}
                	
                }
                else if (menuName.equals("Room")) {
                	
                	switch(menuItemText) {
              	  case "Create":
              		createRoom(mainPanel,dashboardC);
              	    break;
              	  case "Read":
              		readRoom(mainPanel,dashboardC);
              	    break;
              	  case "Update":
              		updateRoom(mainPanel,dashboardC);
                	    break;
              	  case "Delete":
              		deleteRoom(mainPanel,dashboardC);
                	    break;
              	  default:
              	    // code block
              	}
                }
               
            }
        };

        // Add action listeners to menu items
        createAdminItem.addActionListener(menuActionListener);
        readAdminItem.addActionListener(menuActionListener);
        updateAdminItem.addActionListener(menuActionListener);
        deleteAdminItem.addActionListener(menuActionListener);
        createClientItem.addActionListener(menuActionListener);
        readClientItem.addActionListener(menuActionListener);
        updateClientItem.addActionListener(menuActionListener);
        deleteClientItem.addActionListener(menuActionListener);
        createRoomItem.addActionListener(menuActionListener);
        readRoomItem.addActionListener(menuActionListener);
        updateRoomItem.addActionListener(menuActionListener);
        deleteRoomItem.addActionListener(menuActionListener);

        setVisible(true);
    }
    protected void deleteClient(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(3, 2));

	    
	    JLabel idLabel = new JLabel("ID:");
	    JTextField idField = new JTextField();
	    JButton submitButton = new JButton("Delete");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(idLabel);
	    mainPanel.add(idField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    // Add action listener to submit button
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            String idText = idField.getText();

	            
	            if (idText.isEmpty()) {
	                resultLabel.setText("Please enter the ID.");
	                return;
	            }

	            int id;
	            try {
	                id = Integer.parseInt(idText);
	                resultLabel.setText(dashboardC.deleteClient(id));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID must be a number.");
	                return;
	            }

	           
	        }
	    });

	   
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	protected void updateClient(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(5, 2));

	    
	    JLabel idLabel = new JLabel("ID:");
	    JTextField idField = new JTextField();
	    JLabel nameLabel = new JLabel("Name:");
	    JTextField nameField = new JTextField();
	    JLabel passLabel = new JLabel("Password:");
	    JPasswordField passField = new JPasswordField();
	    JButton submitButton = new JButton("Update");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(idLabel);
	    mainPanel.add(idField);
	    mainPanel.add(nameLabel);
	    mainPanel.add(nameField);
	    mainPanel.add(passLabel);
	    mainPanel.add(passField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            String idText = idField.getText();
	            String name = nameField.getText();
	            String pass = new String(passField.getPassword());

	            if (idText.isEmpty()) {
	                resultLabel.setText("Please enter the ID.");
	                return;
	            }

	            
	            int id;
	            try {
	                id = Integer.parseInt(idText);
	                
	                resultLabel.setText(dashboardC.updateClient(id, name, pass));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID must be a number.");
	                return;
	            }


	           
	        }
	    });

	    
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	protected void readClient(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(4, 2));

	    
	    JLabel numberLabel = new JLabel("Enter a number:");
	    JTextField numberField = new JTextField();
	    
	    
	    JButton submitButton = new JButton("Submit");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(numberLabel);
	    mainPanel.add(numberField);
	   
	    
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           
	            String numberText = numberField.getText();
	           

	            
	            if (!numberText.matches("\\d+")) {
	                
	                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }else {
	            	int number = Integer.parseInt(numberText);
	            	
	            	Client client = new Client();
	            	client = dashboardC.readClient(number);
	            	if(client != null) {
	            		 resultLabel.setText("id: " + client.getId() + ", name: " + client.getName()+ ", pass: " + client.getPass());
	            	}else {
	            		 resultLabel.setText("client with this id doesn't exists");
	            	}
	            	  
	            }
	         
	        }
	    });

	  
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	private void createClient(JPanel mainPanel,DashboardC dashboardC) {
        
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(4, 2));

        
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("");

        // Add components to main panel
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(passLabel);
        mainPanel.add(passField);
        mainPanel.add(submitButton);
        mainPanel.add(resultLabel);

        // Add action listener to submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pass = new String(passField.getPassword());
                
                
                // Display result
                resultLabel.setText(dashboardC.createClient(name,pass));
            }
        });

      
        mainPanel.revalidate();
        mainPanel.repaint();
    }
	protected void deleteRoom(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(3, 2));

	    
	    JLabel roomIdLabel = new JLabel("Room ID:");
	    JTextField roomIdField = new JTextField();
	    JButton submitButton = new JButton("Delete");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(roomIdLabel);
	    mainPanel.add(roomIdField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	   
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            String idText = roomIdField.getText();

	            
	            if (idText.isEmpty()) {
	                resultLabel.setText("Please enter the ID.");
	                return;
	            }

	            int id;
	            try {
	                id = Integer.parseInt(idText);
	                resultLabel.setText(dashboardC.deleteRoom(id));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID must be a number.");
	                return;
	            }

	           
	        }
	    });

	   
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	protected void updateRoom(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(7, 2)); 

	    // Panel to display reservation information
	    List<Reservation> reservations = dashboardC.findAllReservations();
	    for (Reservation reservation : reservations) {
	        JLabel reservationLabel1 = new JLabel("Request ID: " + reservation.getRequestID());
	        JLabel reservationLabel2 = new JLabel("Client ID: " + reservation.getClientID());
	        mainPanel.add(reservationLabel1); // Add reservation information labels directly to main panel
	        mainPanel.add(reservationLabel2);
	    }

	    JLabel clientIdLabel = new JLabel("Client Id:");
	    JTextField clientIdField = new JTextField();

	    JLabel roomIdLabel = new JLabel("Room Id:");
	    JTextField roomIdField = new JTextField();
	    JLabel requestIdLabel = new JLabel("Request Id:");
	    JTextField requestIdTextField = new JTextField();
	    JButton submitButton = new JButton("Update");
	    JLabel resultLabel = new JLabel("");

	    mainPanel.add(clientIdLabel);
	    mainPanel.add(clientIdField);

	    mainPanel.add(roomIdLabel);

	    mainPanel.add(roomIdField);
	    mainPanel.add(requestIdLabel);
	    mainPanel.add(requestIdTextField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    // Add action listener to submit button
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String clientIdText = clientIdField.getText();
	            String requestIdText = requestIdTextField.getText();
	            String roomIdText = roomIdField.getText();

	           
	            if (clientIdText.isEmpty() || roomIdText.isEmpty() || requestIdText.isEmpty()) {
	                resultLabel.setText("Please fill in all fields.");
	                return;
	            }

	           
	            int clientId;
	            int roomId;
	            int requestId;
	            try {
	                clientId = Integer.parseInt(clientIdText);
	                requestId = Integer.parseInt(requestIdText);
	                roomId = Integer.parseInt(roomIdText);

	                resultLabel.setText(dashboardC.acceptReservation(clientId, roomId, requestId));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID and duration must be numbers.");
	            }
	        }
	    });

	    mainPanel.revalidate();
	    mainPanel.repaint();
	}


	protected void readRoom(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(4, 2));

	    
	    JLabel numberLabel = new JLabel("Enter a number:");
	    JTextField numberField = new JTextField();
	    
	    
	    JButton submitButton = new JButton("Submit");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(numberLabel);
	    mainPanel.add(numberField);
	   
	    
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	  
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           
	            String numberText = numberField.getText();
	           

	            
	            if (!numberText.matches("\\d+")) {
	                
	                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }else {
	            	int number = Integer.parseInt(numberText);
	            	
	            	Room room = new Room();
	            	room = dashboardC.readRoom(number);
	            	if(room != null) {
	            		 resultLabel.setText("id: " + room.getId() + ", reservation duration: " + room.getReservationDuration() );
	            	}else {
	            		 resultLabel.setText("room with this id doesn't exists");
	            	}
	            	  
	            }
	         
	        }
	    });

	  
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	private void createRoom(JPanel mainPanel,DashboardC dashboardC) {
        
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(4, 2));

        
        JLabel id = new JLabel("id:");
        JTextField idField = new JTextField();
        
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("");

       
        mainPanel.add(id);
        mainPanel.add(idField);
        mainPanel.add(submitButton);
        mainPanel.add(resultLabel);
        
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	 if (idField.getText().isEmpty()) {
 	                resultLabel.setText("Please enter the ID.");
 	                return;
 	            }
            	 Integer roomId;
 	            try {
 	                roomId =Integer.parseInt(idField.getText()) ;
 	            
 	                resultLabel.setText(dashboardC.createRoom(roomId));
 	            } catch (NumberFormatException ex) {
 	                resultLabel.setText("ID must be a number.");
 	                return;
 	            }
            	
                
                
                
            }
        });

      
        mainPanel.revalidate();
        mainPanel.repaint();
    }
	protected void deleteAdmin(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(3, 2));

	    
	    JLabel idLabel = new JLabel("ID:");
	    JTextField idField = new JTextField();
	    JButton submitButton = new JButton("Delete");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(idLabel);
	    mainPanel.add(idField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	   
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            String idText = idField.getText();

	            
	            if (idText.isEmpty()) {
	                resultLabel.setText("Please enter the ID.");
	                return;
	            }

	            int id;
	            try {
	                id = Integer.parseInt(idText);
	                resultLabel.setText(dashboardC.deleteAdmin(id));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID must be a number.");
	                return;
	            }

	           
	        }
	    });

	   
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	protected void updateAdmin(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(5, 2));

	    
	    JLabel idLabel = new JLabel("ID:");
	    JTextField idField = new JTextField();
	    JLabel nameLabel = new JLabel("Name:");
	    JTextField nameField = new JTextField();
	    JLabel passLabel = new JLabel("Password:");
	    JPasswordField passField = new JPasswordField();
	    JButton submitButton = new JButton("Update");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(idLabel);
	    mainPanel.add(idField);
	    mainPanel.add(nameLabel);
	    mainPanel.add(nameField);
	    mainPanel.add(passLabel);
	    mainPanel.add(passField);
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            
	            String idText = idField.getText();
	            String name = nameField.getText();
	            String pass = new String(passField.getPassword());

	            if (idText.isEmpty()) {
	                resultLabel.setText("Please enter the ID.");
	                return;
	            }

	            
	            int id;
	            try {
	                id = Integer.parseInt(idText);
	                
	                resultLabel.setText(dashboardC.updateAdmin(id, name, pass));
	            } catch (NumberFormatException ex) {
	                resultLabel.setText("ID must be a number.");
	                return;
	            }


	           
	        }
	    });

	    
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	protected void readAdmin(JPanel mainPanel, DashboardC dashboardC) {
	    mainPanel.removeAll();
	    mainPanel.setLayout(new GridLayout(4, 2));

	    
	    JLabel numberLabel = new JLabel("Enter a number:");
	    JTextField numberField = new JTextField();
	    
	    
	    JButton submitButton = new JButton("Submit");
	    JLabel resultLabel = new JLabel("");

	    
	    mainPanel.add(numberLabel);
	    mainPanel.add(numberField);
	   
	    
	    mainPanel.add(submitButton);
	    mainPanel.add(resultLabel);

	    
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           
	            String numberText = numberField.getText();
	           

	            
	            if (!numberText.matches("\\d+")) {
	                
	                JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }else {
	            	int number = Integer.parseInt(numberText);
	            	
	            	Admin admin = new Admin();
	            	admin = dashboardC.readAdmin(number);
	            	if(admin != null) {
	            		 resultLabel.setText("id: " + admin.getId() + ", name: " + admin.getName()+ ", pass: " + admin.getPass());
	            	}else {
	            		 resultLabel.setText("admin with this id doesn't exists");
	            	}
	            	  
	            }
	         
	        }
	    });

	  
	    mainPanel.revalidate();
	    mainPanel.repaint();
	}

	private void createAdmin(JPanel mainPanel,DashboardC dashboardC) {
        
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(4, 2));

        
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel("");

       
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(passLabel);
        mainPanel.add(passField);
        mainPanel.add(submitButton);
        mainPanel.add(resultLabel);

       
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pass = new String(passField.getPassword());
                
                
              
                resultLabel.setText(dashboardC.createAdmin(name,pass));
            }
        });

      
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new AdminDashboard("AdminUser"));
    }
}

