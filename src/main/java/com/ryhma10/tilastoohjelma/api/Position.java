package com.ryhma10.tilastoohjelma.api;

public class Position extends ApiData {
	
	String position;

	/**
	 * Constructor
	 * @param position
	 */
	public Position(String position) {
		this.position = position;
	}

	/**
	 * Method to get position
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Method to set position
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

}
