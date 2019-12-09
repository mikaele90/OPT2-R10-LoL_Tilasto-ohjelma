package model;

import com.ryhma10.tilastoohjelma.model.*;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class ModelTest{


	static ModelAccessObject modelDAO = new ModelAccessObject();
	private static SoftwareProfile p1 = new SoftwareProfile("junit1", "test1", null, null, null, null);
	private static SoftwareProfile p2 = new SoftwareProfile("junit2", "test2", null, null, null, null);
	private static Gamedata g1 = new Gamedata(11, "testPlayer1", "Elise", 6, 2, 7 , "win", "jungle","bronze");
	private static Gamedata g2 = new Gamedata (12, "testPlayer2", "Lux", 3, 5, 10 , "win", "support","bronze");
	private static Gamedata g3 = new Gamedata(13, "testPlayer1", "Nami", 5, 3, 8 , "lose", "carry","bronze");
	private static Item i1 = new Item("Raptor Cloak","Void Staff","Oracle Lens","Sheen","Relic Shield","Zephyr", null);
	private static Item i2 = new Item("Wit's End","Vampiric Scepter","Tiamat","Statikk Shiv","Thornmail","Trinity Force", null);
	private static Item i3 = new Item("Phage","Ravenous Hydra","Rod of Ages","Phantom Dancer","Pickaxe","Umbral Glaive", null);
	private static Team t1 = new Team("Grave", "Heimerdinger", "Fizz", "Neeko", "Fiora", "Morgana", "Sona", "Teemo", "Olaf");
	private static Team t2 = new Team("Volibear", "Veigar", "Taric", "Zed", "Nasus", "Pyke", "Sona", "Teemo", "Olaf");
	private static Team t3 = new Team("Twisted Fate", "Ziggs", "Vi", "Rammus", "Lulu", "Kled", "Azir", "Jinx", "Quinn");
	private static Additional a1 = new Additional(8, 7, "gamemode", 16000, 160,"date", 4, 5);
	private static Additional a2= new Additional(16, 45,"gamemode", 14000, 120,"date", 16, 10);
	private static Additional a3 = new Additional(8, 7, "gamemode", 10000, 180,"date", 7, 3);
	
	/**
	 * Empties database and then adds two profile and three gamedatas to database
	 */
	@BeforeAll
	static void addNeeded() {
		modelDAO.deleteAdditonal();
		modelDAO.deleteTeam();
		modelDAO.deleteItem();
		modelDAO.deleteGamedata();
		modelDAO.deleteProfile();
		modelDAO.addProfile(p1);
		modelDAO.addProfile(p2);
		modelDAO.createGamedata("junit1", g1, i1, t1, a1);
		modelDAO.createGamedata("junit2", g2, i2, t2, a2);
		modelDAO.createGamedata("junit1", g3, i3, t3, a3);
	}

	
	/**
	 * Test reading profiledata from database
	 * returns profiledata
	 */
	@Test
	public void testReadProfile() {
		SoftwareProfile testProfile = modelDAO.readProfile("junit2");
		assertEquals("junit2", testProfile.getProfileName(), "wrong name");
		assertEquals("test2", testProfile.getProfilePassword(), "wrong password");
	}

	/**
	 * Test gpm calcultaor
	 * return number in double format
	 */
	@Test
	public void testCountGpm() {
		double gold = 23000;
		double time = 120;
		assertEquals(192, modelDAO.gpmCalculus(time, gold), "Calculus is wrong");

	}
	
	/**
	 * Tests reading one line from Additional table
	 * return Additional object and test check all the values
	 */
	@Test
	public void testReadAdditionalData() {
		Additional additionalResult = modelDAO.readAdditionalData(13);
		assertEquals(8, additionalResult.getDamagedealt(), "Wrong result at damage dealt");
		assertEquals(7, additionalResult.getDamagetaken(), "Wrong result at damage taken");
		assertEquals("gamemode", additionalResult.getGamemode(), "Wrong result at gamemode");
		assertEquals(10000, additionalResult.getGold(), "Wrong result at gold");
		assertEquals(180, additionalResult.getDuration(), "Wrong result at duration");
		assertEquals("date", additionalResult.getDate(), "Wrong result");
		assertEquals(7, additionalResult.getWardsScore(), "Wrong result");
		assertEquals(3, additionalResult.getCreepScore(), "Wrong result");
		
	}
	
	/**
	 * Tests reading one line from Team table
	 * return Team object and test check all the values
	 */
	@Test
	public void testReadTeam() {
		Team teamResult = modelDAO.readTeamComposition(12);
		assertEquals("Volibear", teamResult.getChampion1(), "Wrong result at cahmpion1");
		assertEquals("Veigar", teamResult.getChampion2(), "Wrong result at cahmpion2");
		assertEquals("Taric", teamResult.getChampion3(), "Wrong result at cahmpion3");
		assertEquals("Zed", teamResult.getChampion4(), "Wrong result at cahmpion4");
		assertEquals("Nasus", teamResult.getEnemyChampion1(), "Wrong result at enemy cahmpion1");
		assertEquals("Pyke", teamResult.getEnemyChampion2(), "Wrong result at enemy cahmpion2");
		assertEquals("Sona", teamResult.getEnemyChampion3(), "Wrong result at enemy cahmpion3");
		assertEquals("Teemo", teamResult.getEnemyChampion4(), "Wrong result at enemy cahmpion4");
		assertEquals("Olaf", teamResult.getEnemyChampion5(), "Wrong result at enemy cahmpion5");
		
	}
	
	/**
	 * Tests reading one line from Gamedata table
	 * return gamedata object and test check all the values
	 */
	@Test void testOneGameReading() {
		Gamedata testGamedata = modelDAO.readOneGame(11);
		assertEquals("testPlayer1", testGamedata.getIngameName(), "Ingame name is wrong");
		assertEquals("Elise", testGamedata.getChampion(), "Champion is wrong");
		assertEquals(6, testGamedata.getKills(), "Kills are wrong");
		assertEquals(2, testGamedata.getDeaths(), "Deaths are wrong");
		assertEquals(7, testGamedata.getAssist(), "Assists are wrong");
		assertEquals("jungle", testGamedata.getPosition(), "Position is wrong");
		assertEquals("bronze", testGamedata.getRank(), "Rank is wrong");
		assertEquals(11, testGamedata.getRiotid(),"RiotID is wrong");
		assertEquals("win", testGamedata.getWinlose(), "Result is wrong");
		assertEquals("Raptor Cloak", testGamedata.getItem().getSlot1());
		assertEquals("Void Staff", testGamedata.getItem().getSlot2());
		assertEquals("Oracle Lens", testGamedata.getItem().getSlot3());
		assertEquals("Sheen", testGamedata.getItem().getSlot4());
		assertEquals("Relic Shield", testGamedata.getItem().getSlot5());
		assertEquals("Zephyr", testGamedata.getItem().getSlot6());
		assertEquals(null, testGamedata.getItem().getSlot7());
		assertEquals("junit1", testGamedata.getSoftwareProfile().getProfileName(), "Profiles name is wrong");
		assertEquals("test1", testGamedata.getSoftwareProfile().getProfilePassword(), "Profiles password is wrong");
		assertEquals(null, testGamedata.getSoftwareProfile().getRiotAPIKey(), "Profiles API key is wrong");
		assertEquals(null, testGamedata.getSoftwareProfile().getDefaultRiotAccount(), "Profiles Riot account is wrong");
		assertEquals(null, testGamedata.getSoftwareProfile().getDefaultRegion(), "Profiles region is wrong");
		assertEquals(null, testGamedata.getSoftwareProfile().getDefaultLanguage(), "Profiles language is wrong");
	}
	
	/**
	 * Test if a game is in the database
	 * returns boolean
	 */
	@Test void testCheckGameTrue() {
		boolean checkGameTrue = modelDAO.checkGame(13);
		assertEquals(true, checkGameTrue, "True Checker give false answer");
		
	}
	
	/**
	 * * Test if a game is in the database
	 * returns boolean
	 */
	@Test void testCheckGameFalse() {
		boolean checkGameFalse = modelDAO.checkGame(0);
		assertEquals(false, checkGameFalse, "False checker give false answer");
		
	}
	
	/**
	 * Tests reading all games from database
	 * return list of Gamedata, tester checks only champion data of each object
	 */
	@Test void testReadGames() {
		List<Gamedata> games = modelDAO.readGames();
		Gamedata game1 = games.get(0);
		Gamedata game2 = games.get(1);
		Gamedata game3 = games.get(2);
		assertEquals("Elise", game1.getChampion(), "False answer about game1");
		assertEquals("Lux", game2.getChampion(), "False answer about game2");
		assertEquals("Nami", game3.getChampion(), "False answer about game3");
	}
	
	/**
	 * Tests reading all the games from database which belongs to one account
	 * return List of gamdata objects and tester check one value of each object
	 */
	@Test void testReadSpecificProfileGames() {
		List<Gamedata> games2 = modelDAO.readSpecificProfilesGames("junit1");
		Gamedata gamee1 = games2.get(0);
		Gamedata gamee2 = games2.get(1);
		assertEquals("Elise", gamee1.getChampion(), "False answer about game1");
		assertEquals("Nami", gamee2.getChampion(), "False answer about game2");
	}
	
	/**
	 * Tests reading all items from database
	 * return is list of Item objecs, checks only one data of each list object
	 */
	@Test void testReadItems() {
		List<Item> items = modelDAO.readItems();
		Item item1 = items.get(0);
		Item item2 = items.get(1);
		Item item3 = items.get(2);
		assertEquals("Raptor Cloak", item1.getSlot1(), "False answer in item1");
		assertEquals("Wit's End", item2.getSlot1(), "False answer in item2");
		assertEquals("Phage", item3.getSlot1(), "False answer in item3");
	}
}