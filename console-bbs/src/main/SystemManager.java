package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SystemManager {
	private String loggedInUserId = null;
	private Scanner sc = new Scanner(System.in);
	private Board board;
	private Map<String, User> users;
	
	private boolean isRunning;
	
	public SystemManager() {
		this.board = new Board();
		this.users = new HashMap<>();
		
		isRunning = true;
	}
	
	public void run() {
		while (isRunning) {
			Menu.printStartMenu();
			int menu = getInputNumber("메뉴");
			
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
				runMainMenu();
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
			
			int menu = getInputNumber("메뉴");
			
			switch (menu) {
				case Menu.LOG_OUT:
					logoutUser();
					shouldExit = true;
					break;
				case Menu.MANAGE_USER:
					runUserMenu();
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
	
	private void runUserMenu() {
		boolean shouldExit = false;

		while (!shouldExit) {
			Menu.printUserMenu();

			int menu = getInputNumber("유저 메뉴");

			switch (menu) {
				case Menu.DELETE_USER:
					shouldExit = deleteUser();
					break;
				case Menu.EDIT_USER:
					editUser();
					break;
				case Menu.GO_BACK:
					return;
			}
		}
	}

	private void loginUser() {
		String id = getInputString("아이디");
		String password = getInputString("비밀번호");

		if (!users.containsKey(id)) {
			System.out.println("회원 아이디가 존재하지 않습니다");
			return;
		}
		
		User user = users.get(id);
		
		if (!user.getPassword().equals(password)) {
			System.out.println("비밀번호가 틀립니다");
			return;
		}
		
		System.out.println("로그인 성공");
		loggedInUserId = id;
	}
	
	private void logoutUser() {
		loggedInUserId = null;
		System.out.println("로그아웃 성공");
	}

	private void addUser() {
		String id = getInputString("아이디");
		String password = getInputString("비밀번호");
		
		if (users.containsKey(id)) {
			System.out.println("이미 존재하는 아이디입니다");
			return;
		}
		
		users.put(id, new User(id, password));
		System.out.println("회원가입 성공");
	}
	
	private boolean deleteUser() {
		User user = users.get(loggedInUserId);

		String password = getInputString("비밀번호");

		if (!user.getPassword().equals(password)) {
			System.out.println("비밀번호가 틀립니다");
			return false;
		}
		
		users.remove(loggedInUserId);
		loggedInUserId = null;
		System.out.println("회원탈퇴 성공");
		
		return true;
	}
	
	private void editUser() {
		String password = getInputString("새 비밀번호");
		String reEnteredPassword = getInputString("새 비밀번호 재입력");
		
		if (!password.equals(reEnteredPassword)) {
			System.out.println("비밀번호 불일치");
			return;
		}
		
		User user = users.get(loggedInUserId);
		user.setPassword(password);
		
		users.replace(loggedInUserId, user);
		
		System.out.println("정보 수정 완료");
	}
	
	private String getInputString(String msg) {
		System.out.print(msg + ": ");
		return sc.next();
	}

	private int getInputNumber(String msg) {
		while (true) {
			String input = getInputString(msg);

			try {
				return Integer.parseInt(input);
			} catch (Exception e) { }
		}
	}	
}
