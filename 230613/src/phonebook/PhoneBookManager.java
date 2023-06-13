package phonebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

// 추가, 조회, 삭제, 종료(종료와 함께 파일에 저장)
public class PhoneBookManager {
	
	// 중복친구 이름기준으로 없게, set컬렉션(중복을 허용하지 않지)으로 만들어보자
	HashSet<PhoneInfo> infoStorage = new HashSet<>();
	File dataFile = new File("c:/temp/phonebook.txt");
	
	private static PhoneBookManager instance;
	private PhoneBookManager() {
		
	}
	public static PhoneBookManager getInstance() {
		if(instance == null) {
			instance = new PhoneBookManager();
		}
		return instance;
	}
	// 등록(추가)
	
	public void inputData() throws MenuChoiceException {
		System.out.println("전화번호 구분");
		System.out.println("1. 일반  2. 대학  3. 회사");
		System.out.println("선택 > ");
		
		
		PhoneInfo info = null;
		int menu = MenuViewer.scn.nextInt();
		MenuViewer.scn.nextLine();
		//메뉴선택 잘못한 예외사항
		if(menu < INPUT_SELECT.NORMAL || menu > INPUT_SELECT.COMPANY) {
			throw new MenuChoiceException(menu);
		}
		
		switch(menu) {
		case INPUT_SELECT.NORMAL:
			info = readFriendInfo();
			break;
		case INPUT_SELECT.UNIV:
			info = readUnivFriendInfo();
			break;
		case INPUT_SELECT.COMPANY:
			info = readCompanyFriendInfo();
			break;
		}
		
		boolean isAdded = infoStorage.add(info); //중복값은 false 정상인경우 true
		if(isAdded) {
			System.out.println("등록을 완료했습니다");
		} else {
			System.out.println("!! 등록에 실패하였습니다 !!");
		}
		
		
	}
	//친구 메소드 (일반, 학교, 친구)
	//일반
	private PhoneInfo readFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.scn.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.scn.nextLine();
		return new PhoneInfo(name, phone);
	}
	//학교
	private PhoneInfo readUnivFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.scn.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.scn.nextLine();
		System.out.println("전공을 입력하세요");
		String major = MenuViewer.scn.nextLine();
		System.out.println("학년을 입력하세요");
		int year = MenuViewer.scn.nextInt();
		MenuViewer.scn.nextLine();
		return new PhoneUnivInfo(name, phone, major, year);
	}
	//회사
	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.scn.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.scn.nextLine();
		System.out.println("회사이름을 입력하세요");
		String company = MenuViewer.scn.nextLine();
		return new PhoneCompanyInfo(name, phone, company);
	}
	
	//종료와 저장
	public void storeToFile() {
		try {
			FileWriter fw = new FileWriter(dataFile);
			Iterator<PhoneInfo> iter = infoStorage.iterator();
			while(iter.hasNext()) {
				fw.write(iter.next().toString()); // 이름 연락처 (전공) (학년) (회사)
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
