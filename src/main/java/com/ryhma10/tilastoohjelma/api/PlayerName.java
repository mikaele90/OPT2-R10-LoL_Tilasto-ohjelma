package com.ryhma10.tilastoohjelma.api;

public class PlayerName extends ApiData {
	
	String playerName;

	public PlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
}
