package com.example.unisole.models;

import com.google.gson.annotations.SerializedName;

public class InventoryMerchantStore{

	@SerializedName("inventory")
	private Inventory inventory;

	@SerializedName("merchantStore")
	private MerchantStore merchantStore;

	public void setInventory(Inventory inventory){
		this.inventory = inventory;
	}

	public Inventory getInventory(){
		return inventory;
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
			"InventoryMerchantStore{" + 
			"inventory = '" + inventory + '\'' + 
			",merchantStore = '" + merchantStore + '\'' + 
			"}";
		}
}