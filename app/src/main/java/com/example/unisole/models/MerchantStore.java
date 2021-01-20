package com.example.unisole.models;

public class MerchantStore{
	private int merchantRating;
	private String merchantStoreName;
	private String email;

	public void setMerchantRating(int merchantRating){
		this.merchantRating = merchantRating;
	}

	public int getMerchantRating(){
		return merchantRating;
	}

	public void setMerchantStoreName(String merchantStoreName){
		this.merchantStoreName = merchantStoreName;
	}

	public String getMerchantStoreName(){
		return merchantStoreName;
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
			"MerchantStore{" + 
			"merchantRating = '" + merchantRating + '\'' + 
			",merchantStoreName = '" + merchantStoreName + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
