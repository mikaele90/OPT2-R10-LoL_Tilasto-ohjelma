package com.ryhma10.tilastoohjelma.api;

public class ItemName extends ApiData {
	
	String itemName;

	public ItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
