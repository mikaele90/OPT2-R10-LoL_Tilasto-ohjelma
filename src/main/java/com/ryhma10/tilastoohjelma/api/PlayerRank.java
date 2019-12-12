package com.ryhma10.tilastoohjelma.api;

public class PlayerRank extends ApiData {
	
	String playerRank;

	/**
	 * Constructor
	 * @param playerRank
	 */
	public PlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	/**
	 * Method to get player rank
	 * @return playerRank
	 */
	public String getPlayerRank() {
		return playerRank;
	}

	/**
	 * Method to set player rank
	 * @param playerRank
	 */
	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}
	
	
}
