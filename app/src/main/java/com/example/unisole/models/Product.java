package com.example.unisole.models;

public class Product{
	private String productId;
	private int productRating;
	private Category category;
	private String productName;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductRating(int productRating){
		this.productRating = productRating;
	}

	public int getProductRating(){
		return productRating;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"Product{" + 
			"productId = '" + productId + '\'' + 
			",productRating = '" + productRating + '\'' + 
			",category = '" + category + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}
