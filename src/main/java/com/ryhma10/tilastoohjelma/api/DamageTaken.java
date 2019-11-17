package com.ryhma10.tilastoohjelma.api;

public class DamageTaken extends ApiData {

	long damageTaken;
	
	public DamageTaken(long damageTaken) {
		this.damageTaken = damageTaken;
	}

	public long getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(long damageTaken) {
		this.damageTaken = damageTaken;
	}
	
}
