package com.ryhma10.tilastoohjelma.model;

import javax.persistence.*;


@Entity
@Table(name="team")

public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="teamid")
	private int teamid;
	
	@Column(name="champion1")
	private String champion1;
	
	@Column(name="champion2")
	private String champion2;
	
	@Column(name="champion3")
	private String champion3;
	
	@Column(name="champion4")
	private String champion4;
	
	@Column(name="enemyChampion1")
	private String enemyChampion1;
	
	@Column(name="enemyChampion2")
	private String enemyChampion2;
	
	@Column(name="enemyChampion3")
	private String enemyChampion3;
	
	@Column(name="enemyChampion4")
	private String enemyChampion4;
	
	@Column(name="enemyChampion5")
	private String enemyChampion5;
	
	@OneToOne
	@JoinColumn(name="game_id", referencedColumnName="gameid", nullable=false)
	private Gamedata gamedata;
	
	/**
	 * Empty constructor
	 */
	public Team() {
		
	}
	
	/**
	 * Team constructor takes all the team members and enemy teams champios as parameter in String format
	 * @param champion1
	 * @param champion2
	 * @param champion3
	 * @param champion4
	 * @param enemyChampion1
	 * @param enemyChampion2
	 * @param enemyChampion3
	 * @param enemyChampion4
	 * @param enemyChampion5
	 */
	public Team(String champion1, String champion2, String champion3, String champion4, 
			String enemyChampion1, String enemyChampion2, String enemyChampion3, 
			String enemyChampion4 , String enemyChampion5) {
		this.champion1 = champion1;
		this.champion2 = champion2;
		this.champion3 = champion3;
		this.champion4 = champion4;
		this.enemyChampion1 = enemyChampion1;
		this.enemyChampion2 = enemyChampion2;
		this.enemyChampion3 = enemyChampion3;
		this.enemyChampion4 = enemyChampion4;
		this.enemyChampion5 = enemyChampion5;
	}
	
	/**
	 * Getter for the Team id
	 * @return teamId
	 */
	public int getTeamid() {
		return teamid;
	}
	
	/**
	 * Setter for the Team id
	 * @param teamid
	 */
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	
	/**
	 * Getter for the champion name
	 * @return champion name in slot 1
	 */
	public String getChampion1() {
		return champion1;
	}
	
	/**
	 * Setter for the champion name in slot 1
	 * @param champion1
	 */
	public void setChampion1(String champion1) {
		this.champion1 = champion1;
	}

	/**
	* Getter for the champion name
	 * @return champion name in slot 2
	 */
	public String getChampion2() {
		return champion2;
	}
	
	/**
	 * Setter for the champion name in slot 2
	 * @param champion2
	 */
	public void setChampion2(String champion2) {
		this.champion2 = champion2;
	}
	
	/**
	 * Getter for the champion name
	 * @return champion name in slot 3
	 */
	public String getChampion3() {
		return champion3;
	}
	
	/**
	 * Setter for the champion name in slot 3
	 * @param champion3
	 */
	public void setChampion3(String champion3) {
		this.champion3 = champion3;
	}
	
	/**
	 * Getter for the champion name
	 * @return champion name in slot 4
	 */
	public String getChampion4() {
		return champion4;
	}
	
	/**
	 * Setter for the champion name in slot 4
	 * @param champion4
	 */
	public void setChampion4(String champion4) {
		this.champion4 = champion4;
	}
	
	/**
	 * Getter for the enemy champion name
	 * @return champion name in slot 1
	 */
	public String getEnemyChampion1() {
		return enemyChampion1;
	}
	
	/**
	 * Setter for the enemy champion name in slot 1
	 * @param enemyChampion1
	 */
	public void setEnemyChampion1(String enemyChampion1) {
		this.enemyChampion1 = enemyChampion1;
	}
	
	/**
	 * Getter for the enemy champion name
	 * @return champion name in slot 2
	 */
	public String getEnemyChampion2() {
		return enemyChampion2;
	}
	/**
	 * Setter for the enemy champion name in slot 2
	 * @param enemyChampion2
	 */
	public void setEnemyChampion2(String enemyChampion2) {
		this.enemyChampion2 = enemyChampion2;
	}
	
	/**
	 * Getter for the enemy champion name
	 * @return champion name in slot 3
	 */
	public String getEnemyChampion3() {
		return enemyChampion3;
	}
	
	/**
	 * Setter for the enemy champion name in slot 3
	 * @param enemyChampion3
	 */
	public void setEnemyChampion3(String enemyChampion3) {
		this.enemyChampion3 = enemyChampion3;
	}
	
	/**
	 * Getter for the enemy champion name
	 * @return champion name in slot 4
	 */
	public String getEnemyChampion4() {
		return enemyChampion4;
	}
	
	/**
	 * Setter for the enemy champion name in slot 4
	 * @param enemyChampion4
	 */
	public void setEnemyChampion4(String enemyChampion4) {
		this.enemyChampion4 = enemyChampion4;
	}
	
	/**
	 * Getter for the enemy champion name
	 * @return champion name in slot 5
	 */
	public String getEnemyChampion5() {
		return enemyChampion5;
	}
	
	/**
	 * Setter for the enemy champion name in slot 5
	 * @param enemyChampion5
	 */
	public void setEnemyChampion5(String enemyChampion5) {
		this.enemyChampion5 = enemyChampion5;
	}
	
	/**
	 * Returns Gamedata object
	 * @return Gamedata gamedata
	 */
	public Gamedata getGamedata() {
		return gamedata;
	}
	
	/**
	 * Setter for the Gamedata object
	 * @param gamedata
	 */
	public void setGamedata(Gamedata gamedata) {
		this.gamedata = gamedata;
	}

}
