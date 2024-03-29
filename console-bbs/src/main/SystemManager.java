package main;

public class SystemManager {
	private String loggedInUserId = null;
	private Board board;
	private UserManager userManager;
	
	private boolean isRunning;
	
	public SystemManager() {
		board = new Board();
		userManager = UserManager.getInstance();
		
		isRunning = true;
	}
	
	public void run() {
		while (isRunning) {
			Menu.printStartMenu();
			int menu = Input.getNumber("유저 메뉴");
			
			runStartMenu(menu);
		}
	}
	
	private void runStartMenu(int menu) {
		switch (menu) {
			case Menu.ADD_USER:
				addUser();
				break;
			case Menu.LOG_IN:
				loginUser();
				break;
			case Menu.EXIT:
				isRunning = false;
				break;
		}
	}
	
	private void runMainMenu() {
		boolean shouldExit = false;

		while (!shouldExit) {
			Menu.printMainMenu();
			
			int menu = Input.getNumber("유저 메뉴");
			
			switch (menu) {
				case Menu.LOG_OUT:
					logoutUser();
					shouldExit = true;
					break;
				case Menu.MANAGE_USER:
					shouldExit = runUserMenu();
					break;
				case Menu.VIEW_POSTING_ALL:
					runPostingMenu();
					break;
				case Menu.EXIT:
					isRunning = false;
					return;
			}
		}
	}
	
	private boolean runUserMenu() {
		while (true) {
			Menu.printUserMenu();

			int menu = Input.getNumber("유저 메뉴");

			switch (menu) {
				case Menu.DELETE_USER:
					if (deleteUser())
						return true;
					break;
				case Menu.EDIT_USER:
					editUser();
					break;
				case Menu.GO_BACK:
					return false;
			}
		}
	}
	
	private void runPostingMenu() {
		while (true) {
			board.viewPostingAll();
			Menu.printPostingMenu();

			int menu = Input.getNumber("글 메뉴");

			switch (menu) {
				case Menu.VIEW_POSTING:
					viewPosting();
					break;
				case Menu.ADD_POSTING:
					addPosting();
					break;
				case Menu.GO_BACK:
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
		if (!board.hasPosting())
			return;

		int index = Input.getNumber("글 번호") - 1;
		
		if (!board.hasPosting(index)) {
			System.out.println("잘못된 번호입니다");
			return;
		}

		Posting posting = board.getPosting(index);
		
		while (true) {
			board.viewPosting(index);
			Menu.printPostingEditMenu();
			
			if (runPostingEditMenu(posting))
				return;
		}
	}
	
	private boolean runPostingEditMenu(Posting posting) {
		int menu = Input.getNumber("글 수정 메뉴");
		
		switch (menu) {
			case Menu.EDIT_POSTING:
				editPosting(posting);
				return true;
			case Menu.DELETE_POSTING:
				deletePosting(posting);
				return true;
			case Menu.GO_BACK:
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
	}
	
	private void deletePosting(Posting posting) {
		if (!posting.getUserId().equals(loggedInUserId)) {
			System.out.println("다른 사람의 글 삭제 불가");
			return;
		}

		board.deletePosting(posting);
		
		System.out.println("글 삭제 성공");
	}
	
	private void editPosting(Posting posting) {
		if (!posting.getUserId().equals(loggedInUserId)) {
			System.out.println("다른 사람의 글 수정 불가");
			return;
		}

		String title = Input.getString("제목");
		String content = Input.getString("내용");

		posting.setTitle(title);
		posting.setContent(content);
		
		board.setPosting(posting);
		
		System.out.println("글 수정 성공");
	}
}
