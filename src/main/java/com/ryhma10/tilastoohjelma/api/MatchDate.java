package com.ryhma10.tilastoohjelma.api;

public class MatchDate extends ApiData {

	String matchDate;

	/**
	 * Constructor
	 * @param matchDate
	 */
	public MatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * Method to get match date
	 * @return matchDate
	 */
	public String getMatchDate() {
		return matchDate;
	}

	/**
	 * Method to set match date
	 * @param matchDate
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	
}
