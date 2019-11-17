package com.ryhma10.tilastoohjelma.api;

public class MatchDate extends ApiData {

	String matchDate;

	public MatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	
}
