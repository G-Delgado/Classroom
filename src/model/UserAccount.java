package model;

import java.util.ArrayList;

public class UserAccount {
	private String userName;
	private String password;
	private String profilePhotoUrl;
	private String gender;
	private ArrayList<String> carrers;
	private String birthday;
	private String favBrowser;
	
	public UserAccount(String us, String pass, String prof, String gen, ArrayList<String> car, String bir, String fav) {
		userName = us;
		password = pass;
		profilePhotoUrl = prof;
		gender = gen;
		carrers = car;
		birthday = bir;
		favBrowser = fav;
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
	
	public String getGender () {
		return gender;
	}
	
	public ArrayList<String> getCarrers() {
		return carrers;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public String getFavoriteBrowser() {
		return favBrowser;
	}
}
