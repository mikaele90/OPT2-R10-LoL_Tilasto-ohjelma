package com.ryhma10.tilastoohjelma.api;

public class GoldEarned extends ApiData {
	
	long goldEarned;

	/**
	 * Constructor
	 * @param goldEarned
	 */
	public GoldEarned(long goldEarned) {
		this.goldEarned = goldEarned;
	}

	/**
	 * Method to get gold earned
	 * @return goldEarned
	 */
	public long getGoldEarned() {
		return goldEarned;
	}

	/**
	 * Method to set gold earned
	 * @param goldEarned
	 */
	public void setGoldEarned(long goldEarned) {
		this.goldEarned = goldEarned;
	}

}
