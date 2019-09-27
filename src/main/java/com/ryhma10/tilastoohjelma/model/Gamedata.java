package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;

@Entity
@Table(name="gamedata")


public class Gamedata {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gameid")
	private int gameid;
	
	@Column(name="champion")
	private String champion;
	
	@Column(name="kills")
	private int kills;
	
	@Column(name="deaths")
	private int deaths;

	@Column(name="assist")
	private int assits;
	
	@Column(name="winlose")
	private String winlose;
	
	@Column(name="positio")
	private String positio;
	
	@Column(name="gpm")
	private int gpm;
	
	@ManyToOne
	@JoinColumn(name="Id", referencedColumnName="Id", nullable=false)
	private Profile profile;
	
	public Gamedata() {
	}
	
	public Gamedata(String champion, int kills, int deaths, int assits,
			String winlose, String positio, int gpm, Profile profile) {
		this.champion = champion;
		this.kills = kills;
		this.deaths = deaths;
		this.assits =assits;
		this.winlose = winlose;
		this.positio = positio;
		this.gpm = gpm;
		this.profile = profile;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssits() {
		return assits;
	}

	public void setAssits(int assits) {
		this.assits = assits;
	}

	public int getGid() {
		return gameid;
	}

	public void setGid(int gameid) {
		this.gameid = gameid;
	}

	public String getWinlose() {
		return winlose;
	}

	public void setWinlose(String winlose) {
		this.winlose = winlose;
	}

	public String getPositio() {
		return positio;
	}

	public void setPositio(String positio) {
		this.positio = positio;
	}

	public int getGpm() {
		return gpm;
	}

	public void setGpm(int gpm) {
		this.gpm = gpm;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	

}
