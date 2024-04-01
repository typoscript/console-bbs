package main;

public class Menu {
	protected final int EXIT = 0;

	protected final int ADD_USER = 1;
	protected final int LOG_IN = 2;

	protected final int LOG_OUT = 1;
	protected final int MANAGE_USER = 2;
	protected final int VIEW_POSTING_ALL = 3;

	protected final int DELETE_USER = 1;
	protected final int EDIT_USER = 2;
	protected final int GO_BACK = 3;
	
	protected final int VIEW_POSTING = 1;
	protected final int ADD_POSTING = 2;

	protected final int EDIT_POSTING = 1;
	protected final int DELETE_POSTING = 2;
	
	protected final int ADMIN_MANAGE_USER = 4;

	protected final int ADMIN_VIEW_USER_ALL = 1;

	protected void printStartMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
	}
	
	protected void printUserMainMenu() {
		System.out.println("[1] 로그아웃");
		System.out.println("[2] 계정 관리");
		System.out.println("[3] 게시글 보기");
		System.out.println("[0] 종료");
	}

	protected void printAdminMainMenu() {
		System.out.println("[1] 로그아웃");
		System.out.println("[2] 계정 관리");
		System.out.println("[3] 게시글 보기");
		System.out.println("[4] 유저 관리");
		System.out.println("[0] 종료");
	}
	
	protected void printUserMenu() {
		System.out.println("[1] 회원탈퇴");
		System.out.println("[2] 정보 수정");
		System.out.println("[3] 뒤로가기");
	}

	protected void printPostingMenu() {
		System.out.println("[1] 글 보기");
		System.out.println("[3] 글쓰기");
		System.out.println("[3] 뒤로가기");
	}

	protected void printPostingEditMenu() {
		System.out.println("[1] 글 수정");
		System.out.println("[2] 글 삭제");
		System.out.println("[3] 뒤로가기");
	}
	
	protected void printUserManagementMenu() {
		System.out.println("[1] 유저 리스트 보기");
		System.out.println("[3] 뒤로가기");
	}
}
