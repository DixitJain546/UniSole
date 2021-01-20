package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("password")
	private String password;

	@SerializedName("userName")
	private String userName;

	@SerializedName("email")
	private String email;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword(){
		return password;
	}

	public String getUserName(){
		return userName;
	}

	public String getEmail(){
		return email;
	}
}