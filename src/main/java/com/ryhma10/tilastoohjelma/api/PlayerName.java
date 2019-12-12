package com.ryhma10.tilastoohjelma.api;

public class PlayerName extends ApiData {
	
	String playerName;

	/**
	 * Constructor
	 * @param playerName
	 */
	public PlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Method to get player name
	 * @return playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Method to set player name
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
}
