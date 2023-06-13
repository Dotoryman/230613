package phonebook;

public class PhoneInfo {
	private String name;
	private String phoneNumber;
	
	public PhoneInfo(String name, String PhoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public void showPhoneInfo() {
		System.out.println("name : " + name);
		System.out.println("phone : " + phoneNumber);
	}
	
	
	// 논리적으로 동일한 객체. hashcode, equals
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		PhoneInfo info = (PhoneInfo) obj;
		if(info.name.compareTo(this.name) == 0){//같으면 0, 크거나 작으면 1 또는 -1)
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return INPUT_SELECT.NORMAL +"," + name + "," + phoneNumber + "\n";
	}
}
