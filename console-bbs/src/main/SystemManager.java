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
				runAddUser();
				break;
			case Menu.LOG_IN:
				runLogin();
				break;
			case Menu.EXIT:
				isRunning = false;
				break;
		}
	}
	
	private void runAddUser() {
		String id = getInputString("아이디");
		String password = getInputString("비밀번호");
		
		if (users.containsKey(id)) {
			System.out.println("이미 존재하는 아이디입니다");
			return;
		}
		
		users.put(id, new User(id, password));
		System.out.println("회원가입 성공");
	}
	
	private void runLogin() {
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
		
		runMainMenu();
	}
	
	private void runMainMenu() {
		while (isRunning) {
			Menu.printMainMenu();
			
			int menu = getInputNumber("메뉴");
			
			switch (menu) {
				case Menu.LOG_OUT:
					runLogout();
					break;
				case Menu.MANAGE_USER:
					runUserMenu();
					break;
				case Menu.VIEW_POSTINGS:
					runViewPostings();
					break;
				case Menu.EXIT:
					isRunning = false;
					break;
			}
		}
	}
	
	private void runLogout() {
		isRunning = false;
		loggedInUserId = null;
		System.out.println("로그아웃 성공");
	}
	
	private void runUserMenu() {
		while (true) {
			Menu.printUserMenu();

			int menu = getInputNumber("유저 메뉴");

			switch (menu) {
				case Menu.DELETE_USER:
					runLogout();
					break;
				case Menu.EDIT_USER:
					runEditUser();
					break;
				case Menu.GO_BACK:
					return;
			}
		}
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
