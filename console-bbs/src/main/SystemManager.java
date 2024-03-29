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
				case Menu.VIEW_POSTINGS:
					//runViewPostings();
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
	
	private void addUser() {
		userManager.addUser();
		System.out.println("회원가입 성공");
	}

	private boolean deleteUser() {
		boolean isDeleted = userManager.deleteUser(loggedInUserId);
		
		if (!isDeleted)
			return false;

		System.out.println("회원탈퇴 성공");
		loggedInUserId = null;

		return true;
	}

	private void editUser() {
		boolean isEdited = userManager.editUser(loggedInUserId);
		
		if (isEdited)
			System.out.println("정보 수정 완료");
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

}
