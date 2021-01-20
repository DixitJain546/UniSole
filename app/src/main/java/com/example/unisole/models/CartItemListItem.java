package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class CartItemListItem{

	@SerializedName("sizeId")
	private int sizeId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("colorId")
	private int colorId;

	@SerializedName("price")
	private int price;

	@SerializedName("cart")
	private Cart cart;

	@SerializedName("cartItemId")
	private String cartItemId;

	public void setSizeId(int sizeId){
		this.sizeId = sizeId;
	}

	public int getSizeId(){
		return sizeId;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
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

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public Cart getCart(){
		return cart;
	}

	public void setCartItemId(String cartItemId){
		this.cartItemId = cartItemId;
	}

	public String getCartItemId(){
		return cartItemId;
	}

	@Override
 	public String toString(){
		return 
			"CartItemListItem{" + 
			"sizeId = '" + sizeId + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",colorId = '" + colorId + '\'' + 
			",price = '" + price + '\'' + 
			",cart = '" + cart + '\'' + 
			",cartItemId = '" + cartItemId + '\'' + 
			"}";
		}
}