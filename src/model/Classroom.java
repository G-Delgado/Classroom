package model;

import java.util.ArrayList;

public class Classroom {
	private ArrayList<UserAccount> accounts;
	
	public Classroom() {
		accounts = new ArrayList<UserAccount>();
	}
	
	public void addStudent(String na, String pa, String pro, String gen, ArrayList<String> cars, String birthday, String fav) {
		UserAccount mockStudent = new UserAccount(na, pa, pro, gen, cars, birthday, fav);
		accounts.add(mockStudent);
	}
	
	public ArrayList<UserAccount> getClassroom() {
		return accounts;
	}
}
	
