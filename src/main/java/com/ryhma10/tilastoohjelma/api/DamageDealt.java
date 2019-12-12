package com.ryhma10.tilastoohjelma.api;

public class DamageDealt extends ApiData {
	
	long damageDealt;
	
	/**
	 * Constructor
	 * @param damageDealt
	 */
	public DamageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
	}

	/**
	 * Method to return damage dealt
	 * @return
	 */
	public long getDamageDealt() {
		return damageDealt;
	}

	/**
	 * Method to set damage dealt
	 * @param damageDealt
	 */
	public void setDamageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
	}

}
