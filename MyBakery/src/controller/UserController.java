package controller;

import model.User;
import view.AdminView;
import view.LoginView;
import view.UserView;

public class UserController {
	public static boolean insertUser(String username, String password, String role) {
		
		if(username.trim().equals("")) {
			return false;
		}
		if(password.trim().equals("")) {
			return false;
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		return user.insert();
	}
	
	public static void showLoginRegisterForm() {
		new LoginView();
	}
	
	public static User getUser(int id) {
		return User.get(id);
	}
	
	
	public static User auth(String username, String password) {
		User user = User.get(username, password);
		if(user != null) {
			if(user.getRole().equals("Admin")) {
				new AdminView(user);
			}else {
				new UserView(user);
			}
		}
		return null;
	}

}
