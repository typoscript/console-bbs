package main;

import java.util.ArrayList;

public class Board {
	private Board instance = new Board();
	private ArrayList<Posting> postings = new ArrayList<>();
	
	private Board() { }
	
	public Posting getPosting(int index) {
		return postings.get(index).clone();
	}
	
	public void viewPosting(int index) {
		System.out.println(postings.get(index));
	}
	
	public void viewPostingAll() {
		if (postings.isEmpty()) {
			System.out.println("글이 없습니다");
			return;
		}
		
		for (int i = postings.size() - 1; i >= 0; i--) {
			Posting posting = postings.get(i);
			
			String postingPreview = String.format(
					"%d) 제목: %s | 글쓴이: %s | 업로드 날짜: %s",
					i + 1, posting.getTitle(), posting.getUserId(), posting.getUploadDate()
				);
			
			System.out.println(postingPreview);
		}
	}
	
	public void addPosting(Posting posting) {
		postings.add(posting);
	}
	
	public void deletePosting(Posting posting) {
		int index = getIndexOfPosting(posting);

		postings.remove(index);
	}
	
	public void setPosting(Posting posting) {
		int index = getIndexOfPosting(posting);
		
		postings.set(index, posting);
	}

	public boolean isEmpty() {
		return postings.isEmpty();
	}
	
	public boolean hasPosting(int index) {
		return index >= 0 && index < postings.size();
	}
	
	private int getIndexOfPosting(Posting posting) {
		for (int i = 0; i < postings.size(); i++) {
			if (postings.get(i).getId() == posting.getId())
				return i;
		}
		
		return -1;
	}
}