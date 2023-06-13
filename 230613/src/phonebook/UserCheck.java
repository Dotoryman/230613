package phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

class User {
	String id;
	String pw;

	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
}

public class UserCheck {
	Scanner scn = new Scanner(System.in);
	HashSet<User> userList = new HashSet<>();
	File ufile = new File("c:/temp/userlist.txt");

	UserCheck() {
		readFromFile();
	}

	// id와 passwd를 입력받으면, 리스트에 있을땐 true, 없을땐 false 가 되도록 만들어보기
	public boolean loginCheck(String id, String pw) {

		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			User curr = iter.next();
			if (curr != null && (curr.id.equals(id) && curr.pw.equals(pw))) {
				return false;
			}
		}
		System.out.println("아이디 또는 비밀번호가 틀렸습니다");
		return true;

	}

	public void readFromFile() {
		// c:/temp/userList.txt
		try {
			FileReader fr = new FileReader(ufile);
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			User uInfo = null;

			while ((str = br.readLine()) != null) {
				String[] user = str.split(" ");
				uInfo = new User(user[0], user[1]);
				userList.add(uInfo);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		userList.add(null);
	}
}
