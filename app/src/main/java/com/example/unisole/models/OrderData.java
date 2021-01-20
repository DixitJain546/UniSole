package com.example.unisole.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderData{

	@SerializedName("cartItemList")
	private List<CartItem> cartItemList;

	@SerializedName("userId")
	private String userId;

	public void setCartItemList(List<CartItem> cartItemList){
		this.cartItemList = cartItemList;
	}

	public List<CartItem> getCartItemList(){
		return cartItemList;
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
			"OrderData{" + 
			"cartItemList = '" + cartItemList + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}