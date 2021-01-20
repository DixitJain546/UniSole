package com.example.unisole.models;

public class Size{
	private int sizeId;
	private int sizeNumber;

	public void setSizeId(int sizeId){
		this.sizeId = sizeId;
	}

	public int getSizeId(){
		return sizeId;
	}

	public void setSizeNumber(int sizeNumber){
		this.sizeNumber = sizeNumber;
	}

	public int getSizeNumber(){
		return sizeNumber;
	}

	@Override
 	public String toString(){
		return 
			"Size{" + 
			"sizeId = '" + sizeId + '\'' + 
			",sizeNumber = '" + sizeNumber + '\'' + 
			"}";
		}
}
