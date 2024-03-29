package main;

public class Posting {
	private final int USER_ID;
	private String content;
	
	public Posting(int userId, String content) {
		this.USER_ID = userId;
		this.content = content;
	}
	
	public int getUserID() {
		return USER_ID;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
