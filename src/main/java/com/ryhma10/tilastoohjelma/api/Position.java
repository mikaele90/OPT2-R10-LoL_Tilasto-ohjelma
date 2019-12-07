package com.ryhma10.tilastoohjelma.api;

public class Position extends ApiData {
	
	String position;

	public Position(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
