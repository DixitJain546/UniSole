package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class UserLoginDetail{

	@SerializedName("password")
	private String password;
	@SerializedName("role")
	private int role;
	@SerializedName("email")
	private String email;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"UserLoginDetail{" + 
			"password = '" + password + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}