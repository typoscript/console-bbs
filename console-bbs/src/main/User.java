package main;

public class User {
	private final String ID;
	private String password;
	
	public User(String id, String password) {
		this.ID = id;
		this.password = password;
	}
	
	public String getId() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public User clone() {
		return new User(ID, password);
	}
}