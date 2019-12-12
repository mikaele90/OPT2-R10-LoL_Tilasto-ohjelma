package com.ryhma10.tilastoohjelma.api;

public class MatchId extends ApiData {
	
	long matchId;

	/**
	 * Constructor
	 * @param matchId
	 */
	public MatchId(long matchId) {
		this.matchId = matchId;
	}

	/**
	 * Method to get match id
	 * @return matchId
	 */
	public long getMatchId() {
		return matchId;
	}

	/**
	 * Method to set match id
	 * @param matchId
	 */
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	
	
}
