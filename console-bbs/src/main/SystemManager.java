package main;

import java.util.List;

public class SystemManager extends Menu {
	private String loggedInUserId = null;
	private Board board;
	private UserManager userManager;
	
	private boolean isRunning;
	
	public SystemManager() {
		board = Board.getInstance();
		userManager = UserManager.getInstance();
		userManager.addUser(new Admin("admin", "1234"));
		
		isRunning = true;
	}
	
	public void run() {
		loadBoardFromFile();
		loadUsersFromFile();

		while (isRunning) {
			printStartMenu();
			int menu = Input.getNumber("유저 메뉴");
			
			runStartMenu(menu);
		}
		
		System.out.println("종료");
	}
	
	private void runStartMenu(int menu) {
		switch (menu) {
			case ADD_USER:
				addUser();
				break;
			case LOG_IN:
				loginUser();
				break;
			case EXIT:
				isRunning = false;
				break;
		}
	}
	
	private void runMainMenu() {
		User user = userManager.getUser(loggedInUserId);
				
		if (user instanceof Admin)
			runAdminMainMenu();
		else 
			runUserMainMenu();
	}
	
	private void runUserMainMenu() {
		boolean shouldExit = false;

		while (!shouldExit) {
			printUserMainMenu();
			
			int menu = Input.getNumber("메뉴");
			
			switch (menu) {
				case LOG_OUT:
					logoutUser();
					shouldExit = true;
					break;
				case MANAGE_USER:
					shouldExit = runUserMenu();
					break;
				case VIEW_POSTING_ALL:
					runPostingMenu();
					break;
				case EXIT:
					isRunning = false;
					return;
			}
		}
		
	}
	
	private void runAdminMainMenu() {
		boolean shouldExit = false;

		while (!shouldExit) {
			printAdminMainMenu();
			
			int menu = Input.getNumber("메뉴");
			
			switch (menu) {
				case LOG_OUT:
					logoutUser();
					shouldExit = true;
					break;
				case MANAGE_USER:
					shouldExit = runUserMenu();
					break;
				case VIEW_POSTING_ALL:
					runPostingMenu();
					break;
				case ADMIN_MANAGE_USER:
					runAdminManageUser();
					break;
				case EXIT:
					isRunning = false;
					return;
			}
		}
	}
	
	private boolean runAdminManageUser() {
		while (true) {
			printUserManagementMenu();

			int menu = Input.getNumber("메뉴");
			
			switch (menu) {
				case ADMIN_VIEW_USER_ALL:
					userManager.printUserAll();
					return true;
				case GO_BACK:
					return true;
			}
		}
	}
	
	private boolean runUserMenu() {
		while (true) {
			printUserMenu();

			int menu = Input.getNumber("유저 메뉴");

			switch (menu) {
				case DELETE_USER:
					if (deleteUser())
						return true;
					break;
				case EDIT_USER:
					editUser();
					break;
				case GO_BACK:
					return false;
			}
		}
	}
	
	private void runPostingMenu() {
		while (true) {
			board.viewPostingAll();
			printPostingMenu();

			int menu = Input.getNumber("글 메뉴");

			switch (menu) {
				case VIEW_POSTING:
					viewPosting();
					break;
				case ADD_POSTING:
					addPosting();
					break;
				case GO_BACK:
					return;
			}
		}
	}
	
	private void addUser() {
		String id = Input.getString("아이디");
		String password = Input.getString("비밀번호");
		
		if (userManager.hasId(id)) {
			System.out.println("이미 존재하는 아이디입니다");
			return;
		}
		
		userManager.addUser(new User(id, password));
		System.out.println("회원가입 성공");

		saveUsersToFile();
	}

	private boolean deleteUser() {
		User user = userManager.getUser(loggedInUserId);

		String password = Input.getString("비밀번호");

		if (!user.getPassword().equals(password)) {
			System.out.println("비밀번호가 틀립니다");
			return false;
		}

		userManager.deleteUser(user);

		System.out.println("회원탈퇴 성공");
		loggedInUserId = null;
		
		saveUsersToFile();

		return true;
	}

	private void editUser() {
		String password = Input.getString("새 비밀번호");
		String reEnteredPassword = Input.getString("새 비밀번호 재입력");
		
		if (!password.equals(reEnteredPassword)) {
			System.out.println("비밀번호 불일치");
			return;
		}
		
		User user = userManager.getUser(loggedInUserId);
		user.setPassword(password);
		
		userManager.setUser(user);

		System.out.println("유저 편집 성공");

		saveUsersToFile();
	}

	private void loginUser() {
		String id = Input.getString("아이디");
		String password = Input.getString("비밀번호");

		if (!userManager.hasId(id)) {
			System.out.println("회원 아이디가 존재하지 않습니다");
			return;
		}
		
		User user = userManager.getUser(id);
		
		if (!user.getPassword().equals(password)) {
			System.out.println("비밀번호가 틀립니다");
			return;
		}
		
		System.out.println("로그인 성공");
		loggedInUserId = id;

		runMainMenu();
	}

	
	private void logoutUser() {
		loggedInUserId = null;
		System.out.println("로그아웃 성공");
	}


	private void viewPosting() {
		if (board.isEmpty())
			return;

		int index = Input.getNumber("글 번호") - 1;
		
		if (!board.hasPosting(index)) {
			System.out.println("잘못된 번호입니다");
			return;
		}

		Posting posting = board.getPosting(index);
		
		while (true) {
			board.viewPosting(index);
			printPostingEditMenu();
			
			if (runPostingEditMenu(posting))
				return;
		}
	}
	
	private boolean runPostingEditMenu(Posting posting) {
		int menu = Input.getNumber("글 수정 메뉴");
		
		switch (menu) {
			case EDIT_POSTING:
				editPosting(posting);
				return true;
			case DELETE_POSTING:
				deletePosting(posting);
				return true;
			case GO_BACK:
				return true;
			default:
				return false;
		}
	}
	
	private void addPosting() {
		String title = Input.getString("제목");
		String content = Input.getString("내용");

		board.addPosting(new Posting(loggedInUserId, title, content));
		
		System.out.println("글 업로드 성공");
		
		saveBoardToFile();
	}
	
	private void deletePosting(Posting posting) {
		if (!canModifyPosting(posting)) {
			System.out.println("다른 사람의 글 삭제 불가");
			return;
		}

		board.deletePosting(posting);
		
		System.out.println("글 삭제 성공");

		saveBoardToFile();
	}
	
	private void editPosting(Posting posting) {
		if (!canModifyPosting(posting)) {
			System.out.println("다른 사람의 글 삭제 불가");
			return;
		}

		String title = Input.getString("제목");
		String content = Input.getString("내용");

		posting.setTitle(title);
		posting.setContent(content);
		
		board.setPosting(posting);
		
		System.out.println("글 수정 성공");

		saveBoardToFile();
	}
	
	private boolean canModifyPosting(Posting posting) {
		User user = userManager.getUser(loggedInUserId);

		return userManager.isAdminUser(user) || posting.getUserId().equals(loggedInUserId);
	}
	
	private void saveUsersToFile() {
		String data = "";
		List<User> users = userManager.getUsers();
		
		for (int i = 0; i < users.size(); i++) {
			data += users.get(i).getId() + "/" + users.get(i).getPassword();
			
			if (i < users.size() - 1)
				data += "\n";
		}

		FileManager.saveUserDataToFile(data);
	}

	private void saveBoardToFile() {
		String data = "";
		List<Posting> postings = board.getPostings();
		
		for (int i = 0; i < postings.size(); i++) {
			Posting posting = postings.get(i);
			
			data += posting.getId() + "/" +
					posting.getUserId() + "/" +
					posting.getUploadDate() + "/" +
					posting.getTitle() + "/" +
					posting.getContent();
			
			if (i < postings.size() - 1)
				data += "\n";
		}

		FileManager.saveBoardDataToFile(data);
	}
	
	private void loadUsersFromFile() {
		String data = FileManager.getUserDataFromFile();

		if (data.isEmpty())
			return;

		String[] users = data.split("\n");
		
		for (String user : users) {
			String[] info = user.split("/");
			
			String id = info[0];
			String password = info[1];
			
			userManager.addUser(new User(id, password));
		}
	}

	private void loadBoardFromFile() {
		String data = FileManager.getBoardDataFromFile();
		
		if (data.isEmpty())
			return;
		
		String[] postings = data.split("\n");
		
		for (String posting : postings) {
			String[] info = posting.split("/");
			
			long id = Long.parseLong(info[0]);
			String userId = info[1];
			String uploadDate = info[2];
			String title = info[3];
			String content = info[4];
			
			board.addPosting(new Posting(id, userId, uploadDate, title, content));
		}
	}
}