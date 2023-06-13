package phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

// 추가, 조회, 삭제, 종료(종료와 함께 파일에 저장)
public class PhoneBookManager {

	// 중복친구 이름기준으로 없게, set컬렉션(중복을 허용하지 않지)으로 만들어보자
	HashSet<PhoneInfo> infoStorage = new HashSet<>();
	File dataFile = new File("c:/temp/phonebook.txt");
	File dataStream = new File("c:/temp/phonebook.dat");

	private static PhoneBookManager instance;

	private PhoneBookManager() {
		readFromFile(); // 저장된 정보를 set컬렉션이 초기화
	}

	public static PhoneBookManager getInstance() {
		if (instance == null) {
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
		// 메뉴선택 잘못한 예외사항
		if (menu < INPUT_SELECT.NORMAL || menu > INPUT_SELECT.COMPANY) {
			throw new MenuChoiceException(menu);
		}

		switch (menu) {
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

		boolean isAdded = infoStorage.add(info); // 중복값은 false 정상인경우 true
		if (isAdded) {
			System.out.println("등록을 완료했습니다");
		} else {
			System.out.println("!! 등록에 실패하였습니다 동명이인이 존재합니다 !!");
		}

	}

	// 친구 메소드 (일반, 학교, 친구)
	// 일반
	private PhoneInfo readFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.scn.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.scn.nextLine();
		return new PhoneInfo(name, phone);
	}

	// 학교
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

	// 회사
	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("이름을 입력하세요");
		String name = MenuViewer.scn.nextLine();
		System.out.println("연락처를 입력하세요");
		String phone = MenuViewer.scn.nextLine();
		System.out.println("회사이름을 입력하세요");
		String company = MenuViewer.scn.nextLine();
		return new PhoneCompanyInfo(name, phone, company);
	}

	// 종료와 저장 storeToStream() =>ObjectOutputStream: Serializable로 선언해야함
	public void storeToFile() {
		System.out.println("종료합니다");
		try {
			FileWriter fw = new FileWriter(dataFile);
			Iterator<PhoneInfo> iter = infoStorage.iterator();
			while (iter.hasNext()) {
				fw.write(iter.next().toString()); // 이름 연락처 (전공) (학년) (회사)
			}
			fw.flush();
			fw.close();
			System.out.println("파일에 저장을 완료했습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 파일 읽기 readFromStream() => ObjectOutputStream 
	public void readFromFile() {
		if(!dataFile.exists()) {
			return;
		}
		try {
			FileReader fr = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(fr);

			String str = "";
			PhoneInfo info = null;
			while ((str = br.readLine()) != null) {
				String[] record = str.split(",");
				int kind = Integer.parseInt(record[0]);
				switch (kind) {
				case INPUT_SELECT.NORMAL:
					info = new PhoneInfo(record[1], record[2]);
					break;
				case INPUT_SELECT.UNIV:
					info = new PhoneUnivInfo(record[1], record[2], record[3], Integer.parseInt(record[4]));
					break;
				case INPUT_SELECT.COMPANY:
					info = new PhoneCompanyInfo(record[1], record[2], record[3]);
					break;
				}
				infoStorage.add(info);
			}
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("읽어들일 파일이 없습니다.");
		}

	}

	// 삭제 (이름기준)
	public void deleteData() {
		System.out.println("삭제할 이름을 입력해주세요.");
		String name = MenuViewer.scn.nextLine();

		Iterator<PhoneInfo> iter = infoStorage.iterator();
		while (iter.hasNext()) {
			PhoneInfo curr = iter.next();
			if(curr.getName().equals(name)) {
				iter.remove();
				System.out.println("삭제가 완료되었습니다.");
				return;
			}
		}
		System.out.println("없는 이름입니다.");
	}

	// 검색
	public void searchData() {
		System.out.println("조회할 이름을 입력해주세요.");
		String name = MenuViewer.scn.nextLine();
		
		PhoneInfo info = search(name);
		if(info == null) {
			System.out.println("없는 이름입니다.");
		} else {
			info.showPhoneInfo();
		}
	}
	
	public PhoneInfo search(String name) {
		Iterator<PhoneInfo> iter = infoStorage.iterator();
		while(iter.hasNext()) {
			PhoneInfo curItem = iter.next();
			if(curItem.getName().equals(name)) {
				return curItem;
			}
		}
		return null;
	}
}
