package phonebook;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner scn = new Scanner(System.in);
	public static void showMenu() {
		System.out.println("================");
		System.out.println("전화번호 관리 : ");
		System.out.println("1. 입력하기");
		System.out.println("2. 검색하기");
		System.out.println("3. 삭제하기");
		System.out.println("4. 종료하기");
		System.out.println("================");
	}
}
