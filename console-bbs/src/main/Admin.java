package main;

public class Admin extends User {
	public Admin(String id, String password) {
		super(id, password);
	}

	@Override
	public Admin clone() {
		return new Admin(getId(), getPassword());
	}
}