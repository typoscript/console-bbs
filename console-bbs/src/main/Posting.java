package main;

public class Posting {
	private final int USER_UID;
	private String content;
	
	public Posting(int userUID, String content) {
		this.USER_UID = userUID;
		this.content = content;
	}
}
