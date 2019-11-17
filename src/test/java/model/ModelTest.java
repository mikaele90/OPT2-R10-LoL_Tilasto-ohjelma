package model;

import com.ryhma10.tilastoohjelma.model.*;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


public class ModelTest{

	static ModelAccessObject modelDAO = new ModelAccessObject();
	private SoftwareProfile p1, p2;
	private Gamedata g1 = new Gamedata("wat", 6, 7, 8,
			"lose", "testi", 5, "kama", "kama", "kama", "kama","kama","kama","junit1");
	private Gamedata g2 = new Gamedata ("jaha", 7, 2, 3,
			"win", "mid", 10, "kama","kama","kama","kama","kama","kama", "junit2");
	private Gamedata g3 = new Gamedata("wut", 6, 7, 8,
			"lose", "testi", 5, "kama","kama","kama","kama","kama","kama", "junit2");
	private Gamedata g4 = new Gamedata ("jaahas", 7, 2, 3,
			"win", "mid", 10, "kama","kama","kama","kama","kama","kama","junit1");
	//private List<Gamedata> vertauslista1 = Arrays.asList(g1, g2, g3, g4);
	//private List<Gamedata> vertauslista2 = Arrays.asList(g2, g3);   TOTEUTETAAN MYÖHEMMIN
	//private List<Gamedata> vertauslista3 = Arrays.asList(g1, g2, g3);
	private double gold, time;

	@Test 
	public void profiiliListaan() {
		p1 = new SoftwareProfile("junit1", "testi", null, null, null, null);
		p2 = new SoftwareProfile("junit2", "jaa", null, null, null, null);
		assertEquals(true, modelDAO.addProfile(p1), "profiili ei mennyt db:hen");
		assertEquals(true, modelDAO.addProfile(p2), "profiili ei mennyt db:hen");
	}


	@Test
	public void testReadProfile() {
		String testName = "junit3";
		String testPass = "testi";
		SoftwareProfile p3 = new SoftwareProfile(testName, testPass, null, null, null, null);
		modelDAO.addProfile(p3);

		Assert.assertEquals(testName, p3.getProfileName());
		Assert.assertEquals(testPass, p3.getProfilePassword());
	}


	@Test
	public void addGamedata() {
		assertEquals(true, modelDAO.createGamedata(g1), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g2), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g3), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g3), "datan lisäiys ei onnistu");
	}


	@Test
	public void countGpm() {
		gold = 23000;
		time = 120;
		assertEquals(192, modelDAO.gpmCalculus(time, gold), "laskettu väärin");

	}



}