package com.ryhma10.tilastoohjelma.api;

public class MatchAssists extends ApiData {
	
	long matchAssists;

	/**
	 * Constructor
	 * @param matchAssists
	 */
	public MatchAssists(long matchAssists) {
		this.matchAssists = matchAssists;
	}

	/**
	 * Method to get match assists
	 * @return matchAssists
	 */
	public long getMatchAssists() {
		return matchAssists;
	}

	/**
	 * Method to set match assists
	 * @param matchAssists
	 */
	public void setMatchAssists(long matchAssists) {
		this.matchAssists = matchAssists;
	}

}
