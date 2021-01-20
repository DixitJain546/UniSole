package com.example.unisole.models;

public class Color{
	private String colorName;
	private int colorId;

	public void setColorName(String colorName){
		this.colorName = colorName;
	}

	public String getColorName(){
		return colorName;
	}

	public void setColorId(int colorId){
		this.colorId = colorId;
	}

	public int getColorId(){
		return colorId;
	}

	@Override
 	public String toString(){
		return 
			"Color{" + 
			"colorName = '" + colorName + '\'' + 
			",colorId = '" + colorId + '\'' + 
			"}";
		}
}
