package main;

public class Posting {
	private final int USER_ID;
	private final String UPLOAD_DATE;
	private String title;
	private String content;
	
	public Posting(int userId, String content) {
		this.USER_ID = userId;
		this.content = content;
		this.UPLOAD_DATE = Date.getCurrentDate();
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
