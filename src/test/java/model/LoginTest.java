package model;

import org.junit.jupiter.api.Test;

import com.ryhma10.tilastoohjelma.model.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

public class LoginTest {
	
	static ModelAccessObject modelDAO = new ModelAccessObject();
	private SoftwareProfile p5;
	private Gamedata g5 = new Gamedata(55, "testPlayer5", "Elise", 6, 2, 7 , "win", "jungle","bronze");
	private Item i5 = new Item("Raptor Cloak","Void Staff","Oracle Lens","Sheen","Relic Shield","Zephyr", null);
	private Team t5 = new Team("Volibear", "Veigar", "Taric", "Zed", "Nasus", "Pyke", "Sona", "Teemo", "Olaf");
	private Additional a5 = new Additional(8, 7, "gamemode", 16000, 160,"date", 4, 5);
	
	/**
	 * Empties the data base before running the test
	 */
	@BeforeAll
	static void addNeeded() {
		modelDAO.deleteAdditonal();
		modelDAO.deleteTeam();
		modelDAO.deleteItem();
		modelDAO.deleteGamedata();
		modelDAO.deleteProfile();
	}
	
	/**
	 * Tester for adding profile to database
	 * returns boolean
	 */
	@Test
	public void testAddProfiletoDb() {
		p5 = new SoftwareProfile("junit5", "test5", null, null, null, null);
		assertEquals(true, modelDAO.addProfile(p5), "Profile adding failed");
	}
    
	/**
	 * Tester for adding gamedata to data base
	 * return boolean
	 */
	@Test
	public void testAddGamedata() {
		assertEquals(true, modelDAO.createGamedata("junit5", g5, i5, t5, a5), "Gamedata 5 insert failed");
	}
}
