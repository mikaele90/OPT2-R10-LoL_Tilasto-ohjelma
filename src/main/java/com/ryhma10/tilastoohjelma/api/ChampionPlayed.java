package com.ryhma10.tilastoohjelma.api;

public class ChampionPlayed extends ApiData {
	
	String championPlayed;
	
	/**
	 * Constructor
	 * @param championPlayed
	 */
	public ChampionPlayed(String championPlayed) {
		this.championPlayed = championPlayed;
	}
	
	/**
	 * Method to return championPlayed
	 * @return championPlayed
	 */
	public String getChampionPlayed() {
		return championPlayed;
	}

	/**
	 * Method to set championPlayed
	 * @param championPlayed
	 */
	public void setChampionPlayed(String championPlayed) {
		this.championPlayed = championPlayed;
	}

}
