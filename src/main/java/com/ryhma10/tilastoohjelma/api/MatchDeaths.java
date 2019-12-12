package com.ryhma10.tilastoohjelma.api;

public class MatchDeaths extends ApiData {
	
	long matchDeaths;

	/**
	 * Constructor
	 * @param matchDeaths
	 */
	public MatchDeaths(long matchDeaths) {
		this.matchDeaths = matchDeaths;
	}

	/**
	 * Method to get match deaths
	 * @return matchDeaths
	 */
	public long getMatchDeaths() {
		return matchDeaths;
	}

	/**
	 * Method to set match deaths
	 * @param matchDeaths
	 */
	public void setMatchDeaths(long matchDeaths) {
		this.matchDeaths = matchDeaths;
	}

}
