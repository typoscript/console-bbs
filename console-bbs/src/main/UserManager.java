package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
	private static UserManager userManager = new UserManager();
	private Map<String, User> users = new HashMap<>();

	private UserManager() { }
	
	public static UserManager getInstance() {
		return userManager;
	}
	
	public boolean isAdminUser(User user) {
		return user instanceof Admin;
	}
	
	public User getUser(String id) {
		return users.get(id).clone();
	}
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		for (String id : this.users.keySet()) {
			User user = getUser(id);
			
			users.add(user);
		}
		
		return users;
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
	
	public void printUserAll() {
		List<String> userIds = new ArrayList<String>(users.keySet());
		
		for (String id : userIds)
			System.out.println(users.get(id));
	}
}
