package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class InventoryPK{

	@SerializedName("product")
	private Product product;

	@SerializedName("size")
	private Size size;

	@SerializedName("color")
	private Color color;

	@SerializedName("merchantId")
	private String merchantId;

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setSize(Size size){
		this.size = size;
	}

	public Size getSize(){
		return size;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public Color getColor(){
		return color;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	@Override
 	public String toString(){
		return 
			"InventoryPK{" + 
			"product = '" + product + '\'' + 
			",size = '" + size + '\'' + 
			",color = '" + color + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			"}";
		}
}