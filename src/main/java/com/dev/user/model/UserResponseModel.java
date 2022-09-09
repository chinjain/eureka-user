package com.dev.user.model;

public class UserResponseModel {

	private String fName;
	private String lName;
	private String email;
	private String userID;

	public UserResponseModel(String fName, String lName, String email, String userID) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.userID = userID;
	}

	public UserResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
