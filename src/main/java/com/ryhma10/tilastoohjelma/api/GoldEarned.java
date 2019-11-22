package com.ryhma10.tilastoohjelma.api;

public class GoldEarned extends ApiData {
	
	long goldEarned;

	public GoldEarned(long goldEarned) {
		this.goldEarned = goldEarned;
	}

	public long getGoldEarned() {
		return goldEarned;
	}

	public void setGoldEarned(long goldEarned) {
		this.goldEarned = goldEarned;
	}

}
