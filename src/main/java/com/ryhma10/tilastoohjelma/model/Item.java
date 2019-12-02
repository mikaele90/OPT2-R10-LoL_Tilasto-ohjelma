package com.ryhma10.tilastoohjelma.model;


import javax.persistence.*;

@Entity
@Table(name="item")

public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="itemid")
	private int itemid;
	
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
	
	@Column(name="slot7")
	private String slot7;
	
	@OneToOne
	@JoinColumn(name="game_id", referencedColumnName="gameid", nullable=false)
	private Gamedata gamedata;
	
	/**
	 * Empty constructor
	 */
	public Item() {
	}
	
	/**
	 * Constructor for the all items
	 * @param slot1 Item slot1
	 * @param slot2 Item slot2
	 * @param slot3 Item slot3
	 * @param slot4 Item slot4
	 * @param slot5 Item slot5
	 * @param slot6 Item slot6
	 * @param slot7 Trinket slot
	 */
	public Item(String slot1, String slot2, String slot3, String slot4,
			String slot5, String slot6, String slot7) {

		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
		this.slot4 = slot4;
		this.slot5 = slot5;
		this.slot6 = slot6;
		this.slot7 = slot7;
	}
	
	/**
	 * Getter for the item slot1
	 * @return item from slot1
	 */
	public String getSlot1() {
		return slot1;
	}

		
	/**
	 * Setter for the item slot1
	 * @param slot1
	 */
	public void setSlot1(String slot1) {
		this.slot1 = slot1;
	}
	
	/**
	 * Getter for the item slot2
	 * @return item from slot2
	 */
	public String getSlot2() {
		return slot2;
	}
	
	/**
	 * Setter for the item slot2
	 * @param slot2
	 */
	public void setSlot2(String slot2) {
		this.slot2 = slot2;
	}
	
	/**
	 * Getter for the item slot3
	 * @return item from slot3
	 */
	public String getSlot3() {
		return slot3;
	}
	
	/**
	 * Setter for the item slot3
	 * @param slot3
	 */
	public void setSlot3(String slot3) {
		this.slot3 = slot3;
	}
	
	/**
	 * Getter for the item slot4
	 * @return item from slot4
	 */
	public String getSlot4() {
		return slot4;
	}
	
	/**
	 * Setter for the item slot4
	 * @param slot4
	 */
	public void setSlot4(String slot4) {
		this.slot4 = slot4;
	}
	
	/**
	 * Getter for the item slot5
	 * @return item from slot5
	 */
	public String getSlot5() {
		return slot5;
	}
	
	/**
	 * Setter for the item slot5
	 * @param slot5
	 */
	public void setSlot5(String slot5) {
		this.slot5 = slot5;
	}
	
	/**
	 * Getter for the item slot6
	 * @return item from slot6
	 */
	public String getSlot6() {
		return slot6;
	}
	
	/**
	 * Setter for the item slot6
	 * @param slot6
	 */
	public void setSlot6(String slot6) {
		this.slot6 = slot6;
	}
	
	/**
	 * Getter for the trinket item
	 * @return trinket from slot7
	 */
	public String getSlot7() {
		return slot7;
	}
	
	/**
	 * Setter for the trinket item
	 * @param slot7
	 */
	public void setSlot7(String slot7) {
		this.slot7 = slot7;
	}
	
	/**
	 * Getter for the Gmaedata object
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
	
	/**
	 * Getter for itemId
	 * @return
	 */
	public int getItemid() {
		return itemid;
	}
	
	/**
	 * setter for the ItemId
	 * @param itemid
	 */
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

}