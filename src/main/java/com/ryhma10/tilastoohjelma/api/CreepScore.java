package com.ryhma10.tilastoohjelma.api;

public class CreepScore extends ApiData {

	
	long creepScore;

	public CreepScore(long creepScore) {
		this.creepScore = creepScore;
	}

	public long getCreepScore() {
		return creepScore;
	}

	public void setCreepScore(long creepScore) {
		this.creepScore = creepScore;
	}
}
