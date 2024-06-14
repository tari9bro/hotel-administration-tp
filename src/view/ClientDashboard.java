package view;

import javax.swing.*;
import controller.DashboardC;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private DashboardC dashboardC;
    public ClientDashboard(String adminName, int clientId) {
        setTitle("Client Dashboard");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2)); // Set layout for the main panel
        add(mainPanel);

        JLabel reservationLabel = new JLabel("Reservation: ");
        JLabel resultLabel = new JLabel("Result: ");
        JButton requestButton = new JButton("Request Reservation");
        JButton cancelButton = new JButton("Cancel Reservation");

        
        JLabel durationLabel = new JLabel("New Duration:");
        JTextField durationField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton cancelEditButton = new JButton("Cancel");

        // Add components to main panel
        mainPanel.add(reservationLabel);
        mainPanel.add(resultLabel);
        mainPanel.add(requestButton);
        mainPanel.add(cancelButton);
        
        mainPanel.add(durationLabel);
        mainPanel.add(durationField);
        mainPanel.add(submitButton);
        mainPanel.add(cancelEditButton);

       
        dashboardC = new DashboardC();
             String requestMSG = null ;
        // Add action listeners to buttons
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ; 
                JOptionPane.showMessageDialog(ClientDashboard.this, dashboardC.requestReservation(clientId,requestMSG));
                reservationLabel.setText("Reservation: " + dashboardC.getReservation(clientId));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int requestId =	dashboardC.findReservationByClientId(clientId).getRequestID();
            	
                JOptionPane.showMessageDialog(ClientDashboard.this, dashboardC.cancelReservation(requestId));
                reservationLabel.setText("Reservation: " + dashboardC.getReservation(clientId));
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                
                int duration = Integer.parseInt(durationField.getText());
                JOptionPane.showMessageDialog(ClientDashboard.this, dashboardC.updateReservation(clientId, duration)); 
                reservationLabel.setText("Reservation: " + dashboardC.getReservation(clientId));
            }
        });

        cancelEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                JOptionPane.showMessageDialog(ClientDashboard.this, "Edit reservation cancelled.");
                reservationLabel.setText("Reservation: " + dashboardC.getReservation(clientId));
            }
        });

     
        reservationLabel.setText("Reservation: " + dashboardC.getReservation(clientId));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientDashboard("AdminUser", 123)); // Example user ID (replace with actual ID)
    }
}
