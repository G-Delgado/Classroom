package model;

import java.util.ArrayList;

public class UserAccount {
	private String username;
	private String password;
	private String profilePhotoUrl;
	private Gender gender;
	private ArrayList<String> careers;
	private String birthday;
	private Browser browser;
	
	public UserAccount(String us, String pass, String prof, String gen, ArrayList<String> car, String bir, String fav) {
		username = us;
		password = pass;
		profilePhotoUrl = prof;
		gender = Gender.valueOf(gen);
		careers = car;
		birthday = bir;
		browser = Browser.valueOf(fav);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String usn) {
		username = usn;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pas) {
		password = pas;
	}
	
	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}
	
	public void setProfilePhotoUrl(String url) {
		profilePhotoUrl = url;
	}
	
	public Gender getGender () {
		return gender;
	}
	
	public void setGender(String gen) {
		gender = Gender.valueOf(gen);
	}
	
	public ArrayList<String> getCareers() {
		return careers;
	}
	
	public void setCareers(ArrayList<String> cars) {
		careers = cars;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthdaY(String birthd) {
		birthday = birthd;
	}
	
	public Browser getBrowser() {
		return browser;
	}
	
	public void setBrowser(String brow) {
		browser = Browser.valueOf(brow);
	}
}
