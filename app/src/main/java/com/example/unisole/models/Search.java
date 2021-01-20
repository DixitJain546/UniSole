package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class Search{

	@SerializedName("image")
	private String image;

	@SerializedName("sizeId")
	private int sizeId;

	@SerializedName("color")
	private String color;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("colorId")
	private int colorId;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("productId")
	private String productId;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setSizeId(int sizeId){
		this.sizeId = sizeId;
	}

	public int getSizeId(){
		return sizeId;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setColorId(int colorId){
		this.colorId = colorId;
	}

	public int getColorId(){
		return colorId;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.productId = id;
	}

	public String getId(){
		return productId;
	}

	@Override
 	public String toString(){
		return 
			"Search{" + 
			"image = '" + image + '\'' + 
			",sizeId = '" + sizeId + '\'' + 
			",color = '" + color + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",colorId = '" + colorId + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + productId + '\'' +
			"}";
		}
}