package com.ryhma10.tilastoohjelma.api;

public class ItemName extends ApiData {
	
	String itemName;

	/**
	 * Constructor
	 * @param itemName
	 */
	public ItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Method to return item name
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Method to set item name
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
