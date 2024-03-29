package main;

public class Posting {
	private final long ID;
	private final String USER_ID;
	private final String UPLOAD_DATE;
	private String title;
	private String content;
	
	public Posting(String userId, String title, String content) {
		this.ID = System.currentTimeMillis();
		this.USER_ID = userId;
		this.UPLOAD_DATE = Date.getCurrentDate();

		this.title = title;
		this.content = content;
	}

	public Posting(int id, String userId, String uploadDate, String title, String content) {
		this.ID = id; 
		this.USER_ID = userId;
		this.UPLOAD_DATE = uploadDate;

		this.title = title;
		this.content = content;
	}
	
	public long getId() {
		return ID;
	}
	
	public String getUserId() {
		return USER_ID;
	}
	
	public String getUploadDate() {
		return UPLOAD_DATE;
	}
	
	public String getTitle() {
		return title;
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
