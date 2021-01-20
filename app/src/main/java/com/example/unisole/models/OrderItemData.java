package com.example.unisole.models;

public class OrderItemData{
	private Product product;
	private Size size;
	private Color color;
	private OrderItem orderItem;
	private MerchantStore merchantStore;

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

	public void setOrderItem(OrderItem orderItem){
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem(){
		return orderItem;
	}

	public void setMerchantStore(MerchantStore merchantStore){
		this.merchantStore = merchantStore;
	}

	public MerchantStore getMerchantStore(){
		return merchantStore;
	}

	@Override
 	public String toString(){
		return 
			"OrderItemData{" + 
			"product = '" + product + '\'' + 
			",size = '" + size + '\'' + 
			",color = '" + color + '\'' + 
			",orderItem = '" + orderItem + '\'' + 
			",merchantStore = '" + merchantStore + '\'' + 
			"}";
		}
}
