package model;

import com.ryhma10.tilastoohjelma.model.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


public class Modeltest{
	
	private ModelAccessObject modelDAO;
	private Profile p1, p2;
	private Gamedata g1, g2, g3, g4;
	//private List<Gamedata> vertauslista1 = Arrays.asList(g1, g2, g3, g4); TOTEUTETAAN MYÖHEMMIN
	//private List<Gamedata> vertauslista2 = Arrays.asList(g2, g3);
	//private List<Gamedata> vertauslista3 = Arrays.asList(g1, g2, g3);
	private double gold, time;
	
	@Test
	public void profiiliListaan() {
		modelDAO = new ModelAccessObject();
		p1 = new Profile("junit1", "testi");
		p2 = new Profile("junit2", "jaa");
		assertEquals(true, modelDAO.addProfile(p1), "profiili ei mennyt db:hen");
		assertEquals(true, modelDAO.addProfile(p2), "profiili ei mennyt db:hen");
	}
	
	
	@Test
	public void testReadProfile() {
			modelDAO = new ModelAccessObject();
	        String testName = "junit3";
	        String testPass = "testi";
	        Profile p3 = new Profile(testName, testPass);
	        modelDAO.addProfile(p3);
	        assertNotNull(modelDAO.readProfile(p3.getName()));
	        Assert.assertEquals(testName, p3.getName());
	        Assert.assertEquals(testPass, p3.getPsw());
	}
	
	
	@Test
	public void addGamedata() {
		modelDAO = new ModelAccessObject();
		g1 = new Gamedata("wat", 6, 7, 8, 
				"lose", "testi", 5, "kama", "kama", "kama", "kama","kama","kama","junit1");
		g2 = new Gamedata ("jaha", 7, 2, 3,
				"win", "mid", 10, "kama","kama","kama","kama","kama","kama", "junit2");
		g3 = new Gamedata("wut", 6, 7, 8, 
				"lose", "testi", 5, "kama","kama","kama","kama","kama","kama", "junit2");
		g4 = new Gamedata ("jaahas", 7, 2, 3,
				"win", "mid", 10, "kama","kama","kama","kama","kama","kama","junit1");
		assertEquals(true, modelDAO.createGamedata(g1), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g2), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g3), "datan lisäiys ei onnistu");
		assertEquals(true, modelDAO.createGamedata(g4), "datan lisäiys ei onnistu");
	}
	
	
	@Test
	public void countGpm() {
		gold = 23000;
		time = 120;
		assertEquals(192, modelDAO.gpmCalculus(time, gold), "laskettu väärin");
		
	}
	
	
	
}