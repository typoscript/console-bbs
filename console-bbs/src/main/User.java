package main;

public class User {
	private final int UID;
	private String id;
	private String password;
	
	public User(int UID, String id, String password) {
		this.UID = UID;
		this.id = id;
		this.password = password;
	}
	
	public int getUID() {
		return UID;
	}
	
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
