package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class Cart{

	@SerializedName("cartId")
	private String cartId;

	@SerializedName("userId")
	private String userId;

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Cart{" + 
			"cartId = '" + cartId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}