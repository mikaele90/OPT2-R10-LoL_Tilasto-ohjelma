package com.ryhma10.tilastoohjelma.api;

public class DamageDealt extends ApiData {
	
	long damageDealt;
	
	public DamageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
	}

	public long getDamageDealt() {
		return damageDealt;
	}

	public void setDamageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
	}

}
