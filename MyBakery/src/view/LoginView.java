package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.Connect;
import controller.UserController;

public class LoginView extends JFrame{
	
	JPanel topPanel, centerPanel, bottomPanel;
	JLabel titleLabel, usernameLabel, passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton, registerButton;
	
	Connect con = Connect.getConnection();

	public LoginView() {
		init();

		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	private void init() {
		topPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(5,1,5,5));
		bottomPanel = new JPanel();
		
		titleLabel = new JLabel("Login");
		titleLabel.setFont(new Font("", Font.BOLD, 30));
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		
		loginButton = new JButton("Login");
		registerButton = new JButton("Don't have account? Register");
		registerButton.setOpaque(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setBorderPainted(false);
		registerButton.setFont(new Font("", Font.PLAIN, 10));
		registerButton.setForeground(Color.BLUE);
		
		topPanel.add(titleLabel);
		
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		centerPanel.add(loginButton);
		

		bottomPanel.add(registerButton);
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);		
		
		loginButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				if(UserController.auth(username, password) != null) {
					dispose();
				}								
			}
		});
		
		
		registerButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==registerButton)
				new RegisterView();
				dispose();				
			}
		});		
	}
}
