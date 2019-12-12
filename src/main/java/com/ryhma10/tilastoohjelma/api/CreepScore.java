package com.ryhma10.tilastoohjelma.api;

public class CreepScore extends ApiData {

	long creepScore;
	
	/**
	 * Constructor
	 * @param creepScore
	 */
	public CreepScore(long creepScore) {
		this.creepScore = creepScore;
	}

	/**
	 * Method to return creep score
	 * @return creepScore
	 */
	public long getCreepScore() {
		return creepScore;
	}

	/**
	 * Method to set creep score
	 * @param creepScore
	 */
	public void setCreepScore(long creepScore) {
		this.creepScore = creepScore;
	}
}
