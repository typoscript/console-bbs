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

	public void setUser(User user) {
		users.replace(user.getId(), user);
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
}
