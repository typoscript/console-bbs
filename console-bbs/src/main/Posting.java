package main;

public class Posting {
	private final long ID;
	private final int USER_ID;
	private final String UPLOAD_DATE;
	private String title;
	private String content;
	
	public Posting(int userId, String title, String content) {
		this.ID = System.currentTimeMillis();
		this.USER_ID = userId;
		this.UPLOAD_DATE = Date.getCurrentDate();

		this.title = title;
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
	
	@Override
	public String toString() {
		String data = String.format("[제목: %s]\n", title);
		data += String.format("[글쓴이: %s | 업로드 날짜: %s]\n", USER_ID, UPLOAD_DATE);
		data += String.format("[내용: %s]\n", content);
		
		return data;
	}
}
