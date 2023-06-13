package phonebook;

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
