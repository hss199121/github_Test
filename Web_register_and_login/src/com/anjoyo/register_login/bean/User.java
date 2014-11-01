package com.anjoyo.register_login.bean;

public class User {
	private String userName;
	private String password;
	private String tel;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public User(String userName, String password, String tel) {
		super();
		this.userName = userName;
		this.password = password;
		this.tel = tel;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", tel=" + tel + "]";
	}
	
}
