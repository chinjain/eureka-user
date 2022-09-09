package com.dev.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

	@NotNull(message = "First Name Can't be null")
	@Size(min = 2,max = 20,message = "first name must be greate that 2 char and less than 20 char")
	private String fName;
	
	@NotNull(message = "Last Name Can't be null")
	@Size(min = 2,max = 20,message = "Last name must be greate that 2 char and less than 20 char")
	private String lName;
	
	@NotNull(message = "Password not empty")
	@Size(min = 8, max = 16)
	private String password;
	
	@Email
	@NotNull
	private String email;

	public User(String fName, String lName, String password, String email) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.password = password;
		this.email = email;
	}

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
