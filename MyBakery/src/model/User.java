package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class User {
	private int id;
	private String username, password, role;
	
	private static Connect con = Connect.getConnection();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean insert() {
		String query = "INSERT INTO user VALUES(NULL, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error insert user");
			return false;
		}
	}
	
	public static User get(int id) {
		User user = null;
		String query = "SELECT * FROM user WHERE id=" + id;
		
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));				
			}
			return user;
		} catch (SQLException e) {
			return user;
		}
	}
	
	
	public static User get(String username, String password) {
		User users = null;
		String query = "SELECT * FROM user WHERE username = '" + username + "'AND password = '" + password + "'";
		ResultSet rs = con.executeQuery(query);
		try {
			if(rs.next()) {
				users = new User();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setRole(rs.getString("role"));			
			}
			return users;
		} catch (SQLException e) {
			return users;
		}
		
	}


	
	

}
