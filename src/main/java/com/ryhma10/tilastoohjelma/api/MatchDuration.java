package com.ryhma10.tilastoohjelma.api;

public class MatchDuration extends ApiData {
	
	long matchDuration;

	/**
	 * Constructor
	 * @param matchDuration
	 */
	public MatchDuration(long matchDuration) {
		this.matchDuration = matchDuration;
	}

	/**
	 * Method to get match duration
	 * @return matchDuration
	 */
	public long getMatchDuration() {
		return matchDuration;
	}

	/**
	 * Method to set match duration
	 * @param matchDuration
	 */
	public void setMatchDuration(long matchDuration) {
		this.matchDuration = matchDuration;
	}

}
