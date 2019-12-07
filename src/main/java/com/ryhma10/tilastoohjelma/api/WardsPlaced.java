package com.ryhma10.tilastoohjelma.api;

public class WardsPlaced extends ApiData {
	
	long wardsPlaced;

	public WardsPlaced(long wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

	public long getWardsPlaced() {
		return wardsPlaced;
	}

	public void setWardsPlaced(long wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

}
