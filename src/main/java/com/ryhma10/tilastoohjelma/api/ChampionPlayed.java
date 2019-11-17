package com.ryhma10.tilastoohjelma.api;

public class ChampionPlayed extends ApiData {
	
	String championPlayed;

	public ChampionPlayed(String championPlayed) {
		this.championPlayed = championPlayed;
	}

	public String getChampionPlayed() {
		return championPlayed;
	}

	public void setChampionPlayed(String championPlayed) {
		this.championPlayed = championPlayed;
	}

}
