package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SystemManager {
	private final int EXIT = 0;

	private final int ADD_USER = 1;
	private final int LOG_IN = 2;

	private final int LOG_OUT = 1;
	private final int MANAGE_USER = 2;
	private final int VIEW_POSTINGS = 3;

	private final int DELETE_USER = 1;
	private final int EDIT_USER = 2;
	private final int GO_BACK = 3;

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
			printStartMenu();
			int menu = getInputNumber("메뉴");
			
			runStartMenu(menu);
		}
	}
	
	private void runStartMenu(int menu) {
		switch (menu) {
			case ADD_USER:
				runAddUser();
				break;
			case LOG_IN:
				runLogin();
				break;
			case EXIT:
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
			printMainMenu();
			
			int menu = getInputNumber("메뉴");
			
			switch (menu) {
				case LOG_OUT:
					runLogout();
					break;
				case MANAGE_USER:
					runUserMenu();
					break;
				case VIEW_POSTINGS:
					runViewPostings();
					break;
				case EXIT:
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
			printUserMenu();

			int menu = getInputNumber("유저 메뉴");

			switch (menu) {
				case DELETE_USER:
					runLogout();
					break;
				case EDIT_USER:
					runEditUser();
					break;
				case GO_BACK:
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
	
	private void printStartMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
	}
	
	private void printMainMenu() {
		System.out.println("[1] 로그아웃");
		System.out.println("[2] 계정 관리");
		System.out.println("[3] 게시글 보기");
		System.out.println("[0] 종료");
	}
	
	private void printUserMenu() {
		System.out.println("[1] 회원탈퇴");
		System.out.println("[2] 정보 수정");
		System.out.println("[3] 뒤로가기");
	}
}
