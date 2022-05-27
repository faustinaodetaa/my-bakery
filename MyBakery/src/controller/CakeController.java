package controller;

import java.util.Vector;

import model.Cake;

public class CakeController {
	public static boolean insertCake(int user_id, String name, int price) {
		Cake cake = new Cake();
		cake.setUser_id(user_id);
		cake.setName(name);
		cake.setPrice(price);
		return cake.insert();
	}
	
	public static boolean deleteCake(int id) {
		
		Cake cake = Cake.get(id);
		return cake.delete();		
	}
	
	public static boolean setStatus(int id, String buyer) {
		Cake cake = Cake.get(id);
		cake.setId(id);
		cake.setBuyer(buyer);
		return cake.setStatus();
	}
	
	public static Vector<Cake> getAllCake(){
		return Cake.getAll();
	}

}
