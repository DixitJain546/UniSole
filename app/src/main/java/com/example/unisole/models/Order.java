package com.example.unisole.models;

public class Order{
	private String orderId;
	private String userId;

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
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
			"Order{" + 
			"orderId = '" + orderId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
