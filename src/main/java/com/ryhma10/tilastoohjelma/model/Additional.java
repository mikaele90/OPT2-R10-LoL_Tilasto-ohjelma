package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;

@Entity
@Table(name="additional")

public class Additional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="additionalid")
	private int additionalid;
	
	@Column(name="damagedealt")
	private long damagedealt;
	
	@Column(name="damagetaken")
	private long damagetaken;
	
	@Column(name="gamemode")
	private String gamemode;
	
	@Column(name="gold")
	private long gold;
	
	@Column(name="duration")
	private long duration;
	
	@Column(name="date")
	private String date;
	
	@OneToOne
	@JoinColumn(name="game_id", referencedColumnName="gameid", nullable=false)
	private Gamedata gamedata;
	
	public Additional() {
		
	}
	
	public Additional(long damagedealt, long damagetaken, String gamemode, long gold, long duration, String date) {
		this.damagedealt = damagedealt;
		this.damagetaken = damagetaken;
		this.gamemode = gamemode;
		this.gold = gold;
		this.duration = duration;
		this.date = date;
	
	}

	public int getAdditionalid() {
		return additionalid;
	}

	public void setAdditionalid(int additionalid) {
		this.additionalid = additionalid;
	}

	public double getDamagedealt() {
		return damagedealt;
	}

	public void setDamagedealt(long damagedealt) {
		this.damagedealt = damagedealt;
	}

	public double getDamagetaken() {
		return damagetaken;
	}

	public void setDamagetaken(long damagetaken) {
		this.damagetaken = damagetaken;
	}

	public String getGamemode() {
		return gamemode;
	}

	public void setGamemode(String gamemode) {
		this.gamemode = gamemode;
	}
	

	public long getGold() {
		return gold;
	}

	public void setGold(long gold) {
		this.gold = gold;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Gamedata getGamedata() {
		return gamedata;
	}

	public void setGamedata(Gamedata gamedata) {
		this.gamedata = gamedata;
	}

}
