package com.ryhma10.tilastoohjelma.api;

public class MatchDeaths extends ApiData {
	
	long matchDeaths;

	public MatchDeaths(long matchDeaths) {
		this.matchDeaths = matchDeaths;
	}

	public long getMatchDeaths() {
		return matchDeaths;
	}

	public void setMatchDeaths(long matchDeaths) {
		this.matchDeaths = matchDeaths;
	}

}
