package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private Map<User, ArrayList<Posting>> board = new HashMap<>();
	
	public boolean addPosting(User user, Posting posting) {
		if (!board.containsKey(user))
			return false;
		
		ArrayList<Posting> postings = board.get(user);
		
		postings.add(posting);
		
		board.put(user, postings);
		
		return true;
	}
	
	public boolean deletePosting(User user, Posting posting) {
		if (!board.containsKey(user))
			return false;
		
		ArrayList<Posting> postings = board.get(user);
		
		for (int i = 0; i < postings.size(); i++) {
			if (postings.get(i).getId() == posting.getId()) {
				postings.remove(i);
				return true;
			}
		}
		
		return false;
	}
}
