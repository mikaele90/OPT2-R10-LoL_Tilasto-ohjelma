package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;


@Entity
@Table(name="gamedata")


public class Gamedata{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gameid")
	private int gameid;
	
	@Column(name="riotid")
	private long riotid;
	
	@Column(name="ingameName")
	private String ingameName;
	
	@Column(name="champion")
	private String champion;
	
	@Column(name="kills")
	private long kills;
	
	@Column(name="deaths")
	private long deaths;

	@Column(name="assist")
	private long assits;
	
	@Column(name="winlose")
	private String winlose;
	
	@Column(name="position")
	private String position;
	
	@Column(name="rank")
	private String rank;
	
	@ManyToOne
	@JoinColumn(name="profile_id", nullable=false)
	private SoftwareProfile SoftwareProfile;
	
	@OneToOne(mappedBy = "gamedata", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Item item;
	
	public Gamedata() {
		
	}
	
	public Gamedata(long riotid, String ingameName, String champion, long kills, long deaths, long assits, 
			String winlose, String position, String rank) {
		this.riotid = riotid;
		this.ingameName = ingameName;
		this.champion = champion;
		this.kills = kills;
		this.deaths = deaths;
		this.assits =assits;
		this.winlose = winlose;
		this.position = position;
		this.rank = rank;
	}
	
	public String getIngameName() {
		return ingameName;
	}

	public void setIngameName(String ingameName) {
		this.ingameName = ingameName;
	}

	public long getRiotid() {
		return riotid;
	}

	public void setRiotid(long riotid) {
		this.riotid = riotid;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

	public long getAssits() {
		return assits;
	}

	public void setAssits(long assits) {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public SoftwareProfile getSoftwareProfile() {
		return SoftwareProfile;
	}

	public void setSoftwareProfile(SoftwareProfile SoftwareProfile) {
		this.SoftwareProfile = SoftwareProfile;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	

}
