package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class GoogleUser{

	@SerializedName("role")
	private int role;

	@SerializedName("userName")
	private String userName;

	@SerializedName("authType")
	private boolean authType;

	@SerializedName("email")
	private String email;

	@SerializedName("token")
	private String token;

	public void setRole(int role){
		this.role = role;
	}

	public int getRole(){
		return role;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setAuthType(boolean authType){
		this.authType = authType;
	}

	public boolean isAuthType(){
		return authType;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"GoogleUser{" + 
			"role = '" + role + '\'' + 
			",userName = '" + userName + '\'' + 
			",authType = '" + authType + '\'' + 
			",email = '" + email + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}