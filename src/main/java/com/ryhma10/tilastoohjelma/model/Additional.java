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
	
	@Column(name="wardsScore")
	private long wardsScore;
	
	@Column(name="creepScore")
	private long creepScore;
	
	@OneToOne
	@JoinColumn(name="game_id", referencedColumnName="gameid", nullable=false)
	private Gamedata gamedata;
	
	/**
	 * Empty constructor
	 */
	public Additional() {
		
	}
	
	/**
	 * Constructor to all the additional data from a single game. Used in Analytical tools
	 * @param damagedealt Long format parameter for the dealt damage during the game
	 * @param damagetaken Long format parameter for the taken damage during the game
	 * @param gamemode String format parameter for the gamemode
	 * @param gold Long format parameter for the earned gold in game
	 * @param duration Long format parameter for the game duration
	 * @param date String format parameter for the date when the game was played
	 * @param wardsScore Long format parameter for the ward score during the game
	 * @param creepScore Long format parameter for the creep score during the game
	 */
	public Additional(long damagedealt, long damagetaken, String gamemode, long gold, long duration,
			String date, long wardsScore, long creepScore) {
		this.damagedealt = damagedealt;
		this.damagetaken = damagetaken;
		this.gamemode = gamemode;
		this.gold = gold;
		this.duration = duration;
		this.date = date;
		this.wardsScore = wardsScore;
		this.creepScore = creepScore;
	
	}
	
	/**
	 * Getter for the Additional id
	 * @return additionalId
	 */
	public int getAdditionalid() {
		return additionalid;
	}
	
	/**
	 * Setter for Id to Additional table
	 * @param additionalid
	 */
	public void setAdditionalid(int additionalid) {
		this.additionalid = additionalid;
	}
	
	/**
	 * Getter for the dealt damage
	 * @return damagedealt
	 */
	public double getDamagedealt() {
		return damagedealt;
	}
	
	/**
	 * Setter for the dealt damage
	 * @param damagedealt
	 */
	public void setDamagedealt(long damagedealt) {
		this.damagedealt = damagedealt;
	}
	
	/**
	 * Getter for the damage taken
	 * @return damagetaken
	 */
	public double getDamagetaken() {
		return damagetaken;
	}
	
	/**
	 * Setter for the damege taken
	 * @param damagetaken
	 */
	public void setDamagetaken(long damagetaken) {
		this.damagetaken = damagetaken;
	}
	
	/**
	 * Getter for the gamemode
	 * @return gamemode
	 */
	public String getGamemode() {
		return gamemode;
	}
	
	/**
	 * Setter for the gamemode
	 * @param gamemode
	 */
	public void setGamemode(String gamemode) {
		this.gamemode = gamemode;
	}
	
	/**
	 * Getter for the gold
	 * @return gold
	 */
	public long getGold() {
		return gold;
	}
	
	/**
	 * Setter for the earned gold
	 * @param gold
	 */
	public void setGold(long gold) {
		this.gold = gold;
	}
	
	/**
	 * Getter for the match duration
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Setter for the match duration
	 * @param duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	/**
	 * Getter for the date
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Setter for the date
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Getter for the Ward score
	 * @return wardScore
	 */
	public long getWardsScore() {
		return wardsScore;
	}
	
	/**
	 * Setter for the Ward score
	 * @param wards
	 */
	public void setWardsScore(long wards) {
		this.wardsScore = wards;
	}
	
	/**
	 * Getter for the Creep Score
	 * @return creepScore
	 */
	public long getCreepScore() {
		return creepScore;
	}
	
	/**
	 * Setter for the Creepscore
	 * @param creepScore
	 */
	public void setCreepScore(long creepScore) {
		this.creepScore = creepScore;
	}
	
	/**
	 * Getter for the Gamedata object
	 * @return Gamedata gamedata
	 */
	public Gamedata getGamedata() {
		return gamedata;
	}
	
	/**
	 * Setter for the Gamedata
	 * @param gamedata
	 */
	public void setGamedata(Gamedata gamedata) {
		this.gamedata = gamedata;
	}

}
