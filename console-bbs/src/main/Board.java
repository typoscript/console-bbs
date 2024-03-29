package main;

import java.util.ArrayList;

public class Board {
	private ArrayList<Posting> postings = new ArrayList<>();
	
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
