package model;

import java.util.ArrayList;

public class UserAccount {
	private String userName;
	private String password;
	private String profilePhotoUrl;
	private Gender gender;
	private ArrayList<String> careers;
	private String birthday;
	private Browser browser;
	
	public UserAccount(String us, String pass, String prof, String gen, ArrayList<String> car, String bir, String fav) {
		userName = us;
		password = pass;
		profilePhotoUrl = prof;
		gender = Gender.valueOf(gen);
		careers = car;
		birthday = bir;
		browser = Browser.valueOf(fav);
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}
	
	public Gender getGender () {
		return gender;
	}
	
	public ArrayList<String> getCareers() {
		return careers;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public Browser getBrowser() {
		return browser;
	}
}
