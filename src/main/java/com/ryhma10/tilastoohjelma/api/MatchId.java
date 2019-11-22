package com.ryhma10.tilastoohjelma.api;

public class MatchId extends ApiData {
	
	long matchId;

	public MatchId(long matchId) {
		this.matchId = matchId;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	
	
}
