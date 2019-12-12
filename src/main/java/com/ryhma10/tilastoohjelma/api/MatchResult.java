package com.ryhma10.tilastoohjelma.api;

public class MatchResult extends ApiData {
	
	String matchResult;

	/**
	 * Constructor
	 * @param matchResult
	 */
	public MatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

	/**
	 * Method to get match result
	 * @return matchResult
	 */
	public String getMatchResult() {
		return matchResult;
	}

	/**
	 * Method to set match result
	 * @param matchResult
	 */
	public void setMatchResult(String matchResult) {
		this.matchResult = matchResult;
	}

}
