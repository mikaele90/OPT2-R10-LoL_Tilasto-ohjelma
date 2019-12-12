package com.ryhma10.tilastoohjelma.api;

public class OtherPlayers extends ApiData {
	
	String otherPlayers;
	
	/**
	 * Constructor
	 * @param otherPlayers
	 */
	public OtherPlayers(String otherPlayers) {
		this.otherPlayers = otherPlayers;
	}

	/**
	 * Method to get other players
	 * @return otherPlayers
	 */
	public String getOtherPlayers() {
		return otherPlayers;
	}

	/**
	 * Method to set other players
	 * @param otherPlayers
	 */
	public void setOtherPlayers(String otherPlayers) {
		this.otherPlayers = otherPlayers;
	}

}
