package com.ryhma10.tilastoohjelma.api;

public class MatchKills extends ApiData {
	
	long matchKills;

	/**
	 * Constructor
	 * @param matchKills
	 */
	public MatchKills(long matchKills) {
		this.matchKills = matchKills;
	}

	/**
	 * Method to get match kills
	 * @return matchKills
	 */
	public long getMatchKills() {
		return matchKills;
	}

	/**
	 * Method to set match kills
	 * @param matchKills
	 */
	public void setMatchKills(long matchKills) {
		this.matchKills = matchKills;
	}

}
