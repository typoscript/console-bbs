package main;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
	private static UserManager userManager = new UserManager();
	private Map<String, User> users = new HashMap<>();

	private UserManager() { }
	
	public static UserManager getInstance() {
		return userManager;
	}
	
	public User getUser(String id) {
		return users.get(id).clone();
	}

	public boolean hasId(String id) {
		return users.containsKey(id);
	}

	public void addUser(User user) {
		users.put(user.getId(), user);
	}
	
	public void deleteUser(User user) {
		users.remove(user.getId());
	}
	
	public boolean editUser(String userId) {
		String password = Input.getString("새 비밀번호");
		String reEnteredPassword = Input.getString("새 비밀번호 재입력");
		
		if (!password.equals(reEnteredPassword)) {
			System.out.println("비밀번호 불일치");
			return false;
		}
		
		User user = users.get(userId);
		user.setPassword(password);
		
		users.replace(userId, user);
		
		return true;
	}	
}
