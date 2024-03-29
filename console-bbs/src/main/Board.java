package main;

import java.util.ArrayList;

public class Board {
	private ArrayList<Posting> postings = new ArrayList<>();
	
	public void viewPosting(int index) {
		System.out.println(postings.get(index));
	}
	
	public void viewPostingAll() {
		if (postings.isEmpty()) {
			System.out.println("글이 없습니다");
			return;
		}
		
		for (int i = 0; i < postings.size(); i++) {
			Posting posting = postings.get(i);
			
			String postingPreview = String.format(
					"%d) 제목: %s | 글쓴이: %s | 업로드 날짜: %s",
					i + 1, posting.getTitle(), posting.getUserId(), posting.getUploadDate()
				);
			
			System.out.println(postingPreview);
		}
	}
	
	public boolean addPosting(Posting posting) {
		postings.add(posting);
		
		return true;
	}
	
	public boolean deletePosting(Posting posting) {
		int index = getIndexOfPosting(posting);
		
		if (index < 0)
			return false;
		
		postings.remove(index);

		return true;
	}
	
	public boolean setPosting(Posting posting) {
		int index = getIndexOfPosting(posting);
		
		if (index < 0)
			return false;
		
		postings.set(index, posting);
		
		return true;
	}
	
	private int getIndexOfPosting(Posting posting) {
		for (int i = 0; i < postings.size(); i++) {
			if (postings.get(i).getId() == posting.getId()) {
				return i;
			}
		}
		
		return -1;
	}
}
