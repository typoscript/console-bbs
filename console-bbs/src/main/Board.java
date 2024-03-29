package main;

import java.util.ArrayList;

public class Board {
	private ArrayList<Posting> postings = new ArrayList<>();
	
	public boolean addPosting(Posting posting) {
		postings.add(posting);
		
		return true;
	}
	
	public boolean deletePosting(Posting posting) {
		for (int i = 0; i < postings.size(); i++) {
			if (postings.get(i).getId() == posting.getId()) {
				postings.remove(i);
				return true;
			}
		}
		
		return false;
	}
}
