package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class Inventory{

	@SerializedName("productImage")
	private String productImage;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("price")
	private int price;

	@SerializedName("inventoryPK")
	private InventoryPK inventoryPK;

	@SerializedName("productDescription")
	private String productDescription;

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setInventoryPK(InventoryPK inventoryPK){
		this.inventoryPK = inventoryPK;
	}

	public InventoryPK getInventoryPK(){
		return inventoryPK;
	}

	public void setProductDescription(String productDescription){
		this.productDescription = productDescription;
	}

	public String getProductDescription(){
		return productDescription;
	}

	@Override
 	public String toString(){
		return 
			"Inventory{" + 
			"productImage = '" + productImage + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",price = '" + price + '\'' + 
			",inventoryPK = '" + inventoryPK + '\'' + 
			",productDescription = '" + productDescription + '\'' + 
			"}";
		}
}