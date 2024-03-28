package main;

import java.util.HashSet;
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

	private int loggedInUserUID = -1;
	private Scanner sc = new Scanner(System.in);
	private Board board;
	private Set<User> users;
	
	private boolean isRunning;
	
	public SystemManager() {
		this.board = new Board();
		this.users = new HashSet<User>();
		
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
	
	private void printUserMenu() {
		System.out.println("[1] 로그아웃");
		System.out.println("[2] 계정 관리");
		System.out.println("[3] 게시글 보기");
		System.out.println("[0] 종료");
	}
	
	private void printAccountMenu() {
		System.out.println("[1] 회원탈퇴");
		System.out.println("[2] 정보 수정");
		System.out.println("[3] 뒤로가기");
	}
}
