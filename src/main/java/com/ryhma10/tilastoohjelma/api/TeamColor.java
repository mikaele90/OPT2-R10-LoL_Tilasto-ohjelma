package com.ryhma10.tilastoohjelma.api;

public class TeamColor extends ApiData {
	
	String teamColor;
	
	/**
	 * Constructor
	 * @param teamColor
	 */
	public TeamColor(String teamColor) {
		this.teamColor = teamColor;
	}

	/**
	 * Method to get team color
	 * @return teamColor
	 */
	public String getTeamColor() {
		return teamColor;
	}
	
	/**
	 * Method to set team color
	 * @param teamColor
	 */
	public void setTeamColor(String teamColor) {
		this.teamColor = teamColor;
	}
}
