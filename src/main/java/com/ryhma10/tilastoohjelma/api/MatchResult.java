package com.ryhma10.tilastoohjelma.api;

public class MatchResult extends ApiData {
	
	String matchResult;

	public MatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	public String getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

}
