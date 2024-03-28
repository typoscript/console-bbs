package main;

import java.util.HashSet;
import java.util.Set;

public class SystemManager {
	private int loggedInUserUID = -1;
	private Board board;
	private Set<User> users;
	
	public SystemManager() {
		this.board = new Board();
		this.users = new HashSet<User>();
	}
	
	public void run() {
		
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
