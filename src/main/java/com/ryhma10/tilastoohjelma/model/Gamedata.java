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
	private double gpm;
	
	@Column(name="slot1")
	private String slot1;
	
	@Column(name="slot2")
	private String slot2;
	
	@Column(name="slot3")
	private String slot3;
	
	@Column(name="slot4")
	private String slot4;
	
	@Column(name="slot5")
	private String slot5;
	
	@Column(name="slot6")
	private String slot6;
	
	@Column(name="pname")
	private String pname;
	
	public Gamedata() {
		
	}
	  
	public Gamedata(String champion, int kills, int deaths, int assits, 
			String winlose, String positio, double gpm,
			String slot1, String slot2, String slot3, String slot4, String slot5, String slot6, String pname) {
		this.champion = champion;
		this.kills = kills;
		this.deaths = deaths;
		this.assits =assits;
		this.winlose = winlose;
		this.positio = positio;
		this.gpm = gpm;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
		this.slot4 = slot4;
		this.slot5 = slot5;
		this.slot6 = slot6;
		this.pname = pname;
	}
 
	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
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

	public String getSlot1() {
		return slot1;
	}

	public void setSlot1(String slot1) {
		this.slot1 = slot1;
	}

	public String getSlot2() {
		return slot2;
	}

	public void setSlot2(String slot2) {
		this.slot2 = slot2;
	}

	public String getSlot3() {
		return slot3;
	}

	public void setSlot3(String slot3) {
		this.slot3 = slot3;
	}

	public String getSlot4() {
		return slot4;
	}

	public void setSlot4(String slot4) {
		this.slot4 = slot4;
	}

	public String getSlot5() {
		return slot5;
	}

	public void setSlot5(String slot5) {
		this.slot5 = slot5;
	}

	public String getSlot6() {
		return slot6;
	}

	public void setSlot6(String slot6) {
		this.slot6 = slot6;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
