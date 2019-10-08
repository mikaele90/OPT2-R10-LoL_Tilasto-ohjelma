package com.ryhma10.tilastoohjelma;

import java.util.List;

import com.ryhma10.tilastoohjelma.model.*;


public class testi2 {
	static ModelAccessObject modelDAO = new ModelAccessObject();
	
	public static void main(String[] args) {
		
	Profile p1 = modelDAO.readProfile("testi");
	System.out.println(p1.getId() + " " + p1.getName());
	
//	Gamedata g66 = new Gamedata("hermanni", 7, 15, 0, "middle", "win", 10,
//			"kama","kama","kama","kama","kama","kama", "testi");
//	
//	Gamedata g67 = new Gamedata("hiiri", 6, 6, 10, "carry", "lose", 500, 
//			"kama","kama","kama","kama","kama","kama", "testi");
//
//	modelDAO.createGamedata(g66);
//	modelDAO.createGamedata(g67);
	
		
//	//tämä ei toimi niinkuin haluaisi
//	List<Gamedata> games = modelDAO.readGames();
//	for(Gamedata game : games) {
//		System.out.println(game.getGameid() + " " + game.getChampion() + " " + game.getKills());
//	}
//	
//	
//	List<Gamedata> pelit = modelDAO.readSpesificGames("jaa");
//	for(Gamedata peli : pelit) {
//		System.out.println(peli.getGameid() + " " + peli.getChampion() + " " + peli.getKills());
//	}
//
//	
//	
//	double goldperminute = modelDAO.gpmCalculus(120, 1500);
//	System.out.println(goldperminute);
	
//	modelDAO.deleteGame(5);
	
	List<Gamedata> games2 = modelDAO.readGames();
	for(Gamedata game : games2) {
		System.out.println(game.getGameid() + " " + game.getChampion() + " " + game.getKills());
	}
	
	}
}
