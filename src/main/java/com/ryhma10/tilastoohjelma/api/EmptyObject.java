package com.ryhma10.tilastoohjelma.api;

public class EmptyObject extends ApiData {
	
	String emptyObject;
	
	/**
	 * Constructor
	 * @param emptyObject
	 */
	public EmptyObject(String emptyObject) {
		this.emptyObject = emptyObject;
	}

	/**
	 * method to get empty object
	 * @return emptyObject
	 */
	public String getEmptyObject() {
		return emptyObject;
	}

	/**
	 * Method to set empty object
	 * @param emptyObject
	 */
	public void setEmptyObject(String emptyObject) {
		this.emptyObject = emptyObject;
	}
}
