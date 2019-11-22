package com.ryhma10.tilastoohjelma.api;

public class TeamColor extends ApiData {
	
	String teamColor;
	
	public TeamColor(String teamColor) {
		this.teamColor = teamColor;
	}

	public String getTeamColor() {
		return teamColor;
	}

	public void setTeamColor(String teamColor) {
		this.teamColor = teamColor;
	}
}
