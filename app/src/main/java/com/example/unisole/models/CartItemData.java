package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class CartItemData{

	@SerializedName("product")
	private Product product;

	@SerializedName("size")
	private Size size;

	@SerializedName("color")
	private Color color;

	@SerializedName("merchantStore")
	private MerchantStore merchantStore;

	@SerializedName("cartItem")
	private CartItem cartItem;

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

	public void setMerchantStore(MerchantStore merchantStore){
		this.merchantStore = merchantStore;
	}

	public MerchantStore getMerchantStore(){
		return merchantStore;
	}

	public void setCartItem(CartItem cartItem){
		this.cartItem = cartItem;
	}

	public CartItem getCartItem(){
		return cartItem;
	}

	@Override
 	public String toString(){
		return 
			"CartItemData{" + 
			"product = '" + product + '\'' + 
			",size = '" + size + '\'' + 
			",color = '" + color + '\'' + 
			",merchantStore = '" + merchantStore + '\'' + 
			",cartItem = '" + cartItem + '\'' + 
			"}";
		}
}