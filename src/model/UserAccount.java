package model;

import java.util.ArrayList;

public class UserAccount {
	private String userName;
	private String password;
	private String profilePhotoUrl;
	private Gender gender;
	private ArrayList<String> carrers;
	private String birthday;
	private Browser favBrowser;
	
	public UserAccount(String us, String pass, String prof, String gen, ArrayList<String> car, String bir, String fav) {
		userName = us;
		password = pass;
		profilePhotoUrl = prof;
		gender = Gender.valueOf(gen);
		carrers = car;
		birthday = bir;
		favBrowser = Browser.valueOf(fav);
	}
	
	public String getUserName() {
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
	
	public ArrayList<String> getCarrers() {
		return carrers;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public Browser getFavoriteBrowser() {
		return favBrowser;
	}
}
