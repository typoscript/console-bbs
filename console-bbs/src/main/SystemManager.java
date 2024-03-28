package main;

import java.util.HashSet;
import java.util.Set;

public class SystemManager {
	private int loggedInUserUID = -1;
	private Board board;
	private Set<User> users;
	
	public SystemManager() {
		this.board = new Board();
		this.users = new HashSet<User>();
	}
}
