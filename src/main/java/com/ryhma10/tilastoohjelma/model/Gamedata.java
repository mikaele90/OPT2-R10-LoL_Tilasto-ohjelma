package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;

@Entity
@Table(name="gamedata")


public class Gamedata{
	
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
	private double gpm;
	
	@ManyToOne
	@JoinColumn(name="profile_id", nullable=false)
	private Profile profile;
	
	@OneToOne(mappedBy = "gamedata", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Item item;
	
	public Gamedata() {
		
	}
	
	public Gamedata(String champion, int kills, int deaths, int assits, 
			String winlose, String positio, double gpm) {
		this.champion = champion;
		this.kills = kills;
		this.deaths = deaths;
		this.assits =assits;
		this.winlose = winlose;
		this.positio = positio;
		this.gpm = gpm;
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

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
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

	public double getGpm() {
		return gpm;
	}

	public void setGpm(double gpm) {
		this.gpm = gpm;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
