package com.ryhma10.tilastoohjelma.api;

public class WardsPlaced extends ApiData {
	
	long wardsPlaced;

	/**
	 * Constructor
	 * @param wardsPlaced
	 */
	public WardsPlaced(long wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

	/**
	 * Method to get wards placed
	 * @return wardsPlaced
	 */
	public long getWardsPlaced() {
		return wardsPlaced;
	}

	/**
	 * Method to set wards placed
	 * @param wardsPlaced
	 */
	public void setWardsPlaced(long wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

}
