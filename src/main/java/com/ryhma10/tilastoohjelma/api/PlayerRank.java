package com.ryhma10.tilastoohjelma.api;

public class PlayerRank extends ApiData {
	
	String playerRank;

	public PlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	public String getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}
	
	
}
