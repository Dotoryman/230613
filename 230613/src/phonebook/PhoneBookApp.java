package phonebook;

import java.util.Scanner;

//PhoneInfo: 이름, 연락처
//PhoneCompanyInfo : 회사 포함
//PhoneUnivInfo : 학교포함
//PhoneBookManager : 기능포함
//MenuChoiceException : 예외사항
//INIT_MENU, INPUT_SELECT : 메뉴
//MenuViewer : 메뉴출력
//추가 조회 삭제
public class PhoneBookApp {
	public static void main(String[] args) {
		PhoneBookManager app = PhoneBookManager.getInstance();
		int menu;
		Scanner scn = new Scanner(System.in);
		UserCheck check = new UserCheck();
		boolean run = true;
		//id, pass : id와 passwd를 입력하세요
		// UserCheck.loginCheck(id,pw);
		// check.loginCheck(id,pw);
		//프로그램 시작
		while(run) {
			System.out.println("관리자 계정을 입력하세요");
			String id = MenuViewer.scn.nextLine();
			System.out.println("비밀번호를 입력하세요");
			String pw = MenuViewer.scn.nextLine();
			run = check.loginCheck(id, pw);

		}
		
		while (true) {
			try {
				MenuViewer.showMenu();
				menu = MenuViewer.scn.nextInt();
				MenuViewer.scn.nextLine();

				if (menu < INIT_MENU.INPUT || menu > INIT_MENU.EXIT) {
					throw new MenuChoiceException(menu);
				}
				switch (menu) {
				case INIT_MENU.INPUT:
					app.inputData();
					break;
					
				case INIT_MENU.SEARCH:
					app.searchData();
					break;
					
				case INIT_MENU.DELETE:
					app.deleteData();
					break;

				case INIT_MENU.EXIT:
					app.storeToFile();
					return;
				}
			} catch (MenuChoiceException e) {
				e.showWrongChoice();
			}
		}
	}
}
