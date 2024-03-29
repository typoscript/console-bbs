package main;

public class UserManager {
	private static UserManager userManager = new UserManager();

	private UserManager() { }
	
	public static UserManager getInstance() {
		return userManager;
	}
}
