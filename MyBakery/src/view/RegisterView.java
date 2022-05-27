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

public class RegisterView extends JFrame{

	JPanel topPanel, centerPanel, bottomPanel;
	JLabel titleLabel, usernameLabel, passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton registerButton, loginButton;
	
	Connect con = Connect.getConnection();

	public RegisterView() {
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

		titleLabel = new JLabel("Register");
		titleLabel.setFont(new Font("", Font.BOLD, 30));
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");

		usernameField = new JTextField();
		passwordField = new JPasswordField();

		registerButton = new JButton("Register");
		loginButton = new JButton("Already Have Account? Login Here");
		loginButton.setForeground(Color.BLUE);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorderPainted(false);

		topPanel.add(titleLabel);

		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		centerPanel.add(registerButton);

		bottomPanel.add(loginButton);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);


		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				String role = "User";
				if(!UserController.insertUser(username, password, role)) {
					JOptionPane.showMessageDialog(null, "Cannot register user");
				}else {
					JOptionPane.showMessageDialog(null, "Successfully registered user!");
					dispose();
					new LoginView();
				}
			}
		});

		
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==loginButton)
					new LoginView();
				dispose();
			}
		});	
	}

}
