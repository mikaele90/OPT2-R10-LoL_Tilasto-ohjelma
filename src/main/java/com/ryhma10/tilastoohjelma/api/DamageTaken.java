package com.ryhma10.tilastoohjelma.api;

public class DamageTaken extends ApiData {

	long damageTaken;
	
	/**
	 * Constructor
	 * @param damageTaken
	 */
	public DamageTaken(long damageTaken) {
		this.damageTaken = damageTaken;
	}

	/**
	 * Method to return damage taken
	 * @return
	 */
	public long getDamageTaken() {
		return damageTaken;
	}

	/**
	 * Method to set damage taken
	 * @param damageTaken
	 */
	public void setDamageTaken(long damageTaken) {
		this.damageTaken = damageTaken;
	}
	
}
