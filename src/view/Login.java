package view;

import javax.swing.*;

import controller.LoginC;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginC loginC;
    
    public Login() {
    	loginC = new LoginC();
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        add(panel);

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
               
                if (!username.isEmpty() && !password.isEmpty()) {
                	
                   
                	String result = loginC.loginToApp(username,password);
                	
                	if(!result.isEmpty()  && !result.equals("Username not found.") && !result.equals("Password not correct.") ) {
                		if(result.equals("admin login succeeded.")) {
                			dispose(); // Close login interface
                			showAdminInterface(username);
                		}else if(result.startsWith("client login succeeded.")){
                		    dispose(); // Close login interface
                		    String clientIdString = result.substring(result.indexOf(".") + 1);
                		    showClientInterface(username, Integer.parseInt(clientIdString.trim()));
                		}

                		
                		
                		
                	} else {
                		Object message = result;
                        JOptionPane.showMessageDialog(Login.this, message, "Login Failed", JOptionPane.ERROR_MESSAGE);

                	}
                    
                    
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }

			
        });
        panel.add(loginButton);

        setVisible(true);
    }
   

   

    private void showClientInterface(String username, int userId) {
    	
    	ClientDashboard clientDashboard = new ClientDashboard(username,userId);
    	clientDashboard.setVisible(true);
    }
    private void showAdminInterface(String username) {
        
        AdminDashboard adminDashboard = new AdminDashboard(  username ); 
        adminDashboard.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}
