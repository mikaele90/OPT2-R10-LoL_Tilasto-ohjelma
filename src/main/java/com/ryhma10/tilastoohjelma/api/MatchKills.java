package com.ryhma10.tilastoohjelma.api;

public class MatchKills extends ApiData {
	
	long matchKills;

	public MatchKills(long matchKills) {
		this.matchKills = matchKills;
	}

	public long getMatchKills() {
		return matchKills;
	}

	public void setMatchKills(long matchKills) {
		this.matchKills = matchKills;
	}

}
