package com.ryhma10.tilastoohjelma.api;

public class MatchDuration extends ApiData {
	
	long matchDuration;

	public MatchDuration(long matchDuration) {
		this.matchDuration = matchDuration;
	}

	public long getMatchDuration() {
		return matchDuration;
	}

	public void setMatchDuration(long matchDuration) {
		this.matchDuration = matchDuration;
	}

}
