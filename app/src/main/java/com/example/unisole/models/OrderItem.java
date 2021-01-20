package com.example.unisole.models;

public class OrderItem{
	private int sizeId;
	private int quantity;
	private String productId;
	private String merchantId;
	private String orderItemId;
	private int colorId;
	private int price;
	private double rating;
	private Order order;

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

	public void setOrderItemId(String orderItemId){
		this.orderItemId = orderItemId;
	}

	public String getOrderItemId(){
		return orderItemId;
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

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"OrderItem{" + 
			"sizeId = '" + sizeId + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",orderItemId = '" + orderItemId + '\'' + 
			",colorId = '" + colorId + '\'' + 
			",price = '" + price + '\'' + 
			",rating = '" + rating + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}
