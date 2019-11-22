package com.ryhma10.tilastoohjelma.api;

public class MatchAssists extends ApiData {
	
	long matchAssists;

	public MatchAssists(long matchAssists) {
		this.matchAssists = matchAssists;
	}

	public long getMatchAssists() {
		return matchAssists;
	}

	public void setMatchAssists(long matchAssists) {
		this.matchAssists = matchAssists;
	}

}
