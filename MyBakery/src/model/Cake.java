package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Cake {
	
	private int id, user_id, price;
	private String name, status, buyer;
	
	private static Connect con = Connect.getConnection();

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean insert() {
		String query = "INSERT INTO cake VALUES(NULL, ?, ?, ?, ?, NULL)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, user_id);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setString(4, "Available");
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error insert cake!");
			return false;
		}
		
	}
	
	public boolean delete() {
		String query = "DELETE FROM cake WHERE id = ? ";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error delete cake!");
			return false;
		}
	}
	
	public boolean setStatus() {
		String query = "UPDATE cake SET status = ?, buyer = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, "Sold");
			ps.setString(2, buyer);
			ps.setInt(3, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("salah");
			return false;
		}
	}
	
	
	public static Cake get(int id) {
		Cake cake = null;
		String query = "SELECT * FROM cake WHERE id=" + id;		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				cake = new Cake();
				cake.setId(rs.getInt("id"));
				cake.setUser_id(rs.getInt("user_id"));
				cake.setName(rs.getString("name"));
				cake.setPrice(rs.getInt("price"));
				cake.setStatus(rs.getString("status"));
			}
			return cake;
		} catch (SQLException e) {
			return cake;
		}
	}
	
	public static Vector<Cake> getAll(){
		String query = "SELECT * FROM cake";
		Vector<Cake> cakeList = new Vector<>();
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Cake cake = new Cake();
				cake.setId(rs.getInt("id"));
				cake.setUser_id(rs.getInt("user_id"));
				cake.setName(rs.getString("name"));
				cake.setPrice(rs.getInt("price"));
				cake.setStatus(rs.getString("status"));
				cake.setBuyer(rs.getString("buyer"));
				cakeList.add(cake);
			}
			return cakeList;
		} catch (SQLException e) {
			return cakeList;
		}
	}
	
	

}
