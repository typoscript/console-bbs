package main;

public class Menu {
	public final int EXIT = 0;

	public final int ADD_USER = 1;
	public final int LOG_IN = 2;

	public final int LOG_OUT = 1;
	public final int MANAGE_USER = 2;
	public final int VIEW_POSTINGS = 3;

	public final int DELETE_USER = 1;
	public final int EDIT_USER = 2;
	public final int GO_BACK = 3;

	public void printStartMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
	}
	
	public void printMainMenu() {
		System.out.println("[1] 로그아웃");
		System.out.println("[2] 계정 관리");
		System.out.println("[3] 게시글 보기");
		System.out.println("[0] 종료");
	}
	
	public void printUserMenu() {
		System.out.println("[1] 회원탈퇴");
		System.out.println("[2] 정보 수정");
		System.out.println("[3] 뒤로가기");
	}
}
