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
	private long assist;
	
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
	
	/**
	 * Empty constructor
	 */
	public Gamedata() {
		
	}
	
	/**
	 * Constructor to insert Gamedata
	 * @param riotid Parameter for match id from Riot Api  (parameter type: Long)
	 * @param ingameName Parameter for player name which player uses in game (parameter type: String)
	 * @param champion Parameter for name of the champion which was used in the game (parameter type: String)
	 * @param kills Parameter for kills which were made by the player (parameter type: Long)
	 * @param deaths Parameter for all the deaths player encountered during the game (parameter type: Long)
	 * @param assist Parameter for all the assists player made throught the game (parameter type: Long)
	 * @param winlose Parameter of the end result of the game (parameter type: String)
	 * @param position Parameter to the players in game position (parameter type: String)
	 * @param rank Parameter for the players rank (parameter type: String)
	 */
	public Gamedata(long riotid, String ingameName, String champion, long kills, long deaths, long assist,
			String winlose, String position, String rank) {
		this.riotid = riotid;
		this.ingameName = ingameName;
		this.champion = champion;
		this.kills = kills;
		this.deaths = deaths;
		this.assist =assist;
		this.winlose = winlose;
		this.position = position;
		this.rank = rank;
	}
	
	/**
	 * Getter for the ingame name
	 * @return players ingame name in String format
	 */
	public String getIngameName() {
		return ingameName;
	}
	
	/**
	 * Setter for the ingame name
	 * @param ingameName 
	 */
	public void setIngameName(String ingameName) {
		this.ingameName = ingameName;
	}
	/**
	 * Getter for the Riot game id
	 * @return RiotId
	 */
	public long getRiotid() {
		return riotid;
	}
	
	/**
	 * Setter for the Riot game id
	 * @param riotid
	 */
	public void setRiotid(long riotid) {
		this.riotid = riotid;
	}
	
	/**
	 * Getter for the Champion name
	 * @return championName
	 */
	public String getChampion() {
		return champion;
	}
	
	/**
	 * Setter for the Champion name
	 * @param champion
	 */
	public void setChampion(String champion) {
		this.champion = champion;
	}
	
	/**
	 * Getter for the kills
	 * @return kills
	 */
	public long getKills() {
		return kills;
	}
	/**
	 * 
	 * @param kills
	 */
	public void setKills(long kills) {
		this.kills = kills;
	}
	/**
	 * Getter for the deaths
	 * @return deaths
	 */
	public long getDeaths() {
		return deaths;
	}
	/**
	 * Setter for the deaths
	 * @param deaths
	 */
	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}
	/**
	 * Getter for the assists
	 * @return
	 */
	public long getAssist() {
		return assist;
	}
	/**
	 * Setter for the assists
	 * @param assist
	 */
	public void setAssist(long assist) {
		this.assist = assist;
	}
	/**
	 * Getter for the auto incremented game id
	 * @return
	 */
	public int getGameid() {
		return gameid;
	}
	/**
	 * Setter for game id
	 * @param gameid
	 */
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	/**
	 * Getter for the game result
	 * @return winlose
	 */
	public String getWinlose() {
		return winlose;
	}
	/**
	 * Setter for the game result
	 * @param winlose
	 */
	public void setWinlose(String winlose) {
		this.winlose = winlose;
	}
	/**
	 * Getter for the players position
	 * @return
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Setter for the players position
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Getter for the players rank
	 * @return
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * Setter for the players rank
	 * @param rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	/**
	 * Gteer for the Profile object
	 * @return SoftwareProfile profile
	 */
	public SoftwareProfile getSoftwareProfile() {
		return SoftwareProfile;
	}
	/**
	 * Setter for the Profile object
	 * @param SoftwareProfile
	 */
	public void setSoftwareProfile(SoftwareProfile SoftwareProfile) {
		this.SoftwareProfile = SoftwareProfile;
	}
	/**
	 * Getter for the Item object
	 * @return Item item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * Setter for the Item object
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

}
