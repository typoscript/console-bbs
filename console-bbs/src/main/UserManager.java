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

	public void addUser() {
		String id = Input.getString("아이디");
		String password = Input.getString("비밀번호");
		
		if (users.containsKey(id)) {
			System.out.println("이미 존재하는 아이디입니다");
			return;
		}
		
		users.put(id, new User(id, password));
	}
	
	public boolean deleteUser(String userId) {
		User user = users.get(userId);

		String password = Input.getString("비밀번호");

		if (!user.getPassword().equals(password)) {
			System.out.println("비밀번호가 틀립니다");
			return false;
		}
		
		users.remove(userId);
		
		return true;
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
