package com.ryhma10.tilastoohjelma;

import java.util.List;

import com.ryhma10.tilastoohjelma.model.*;

public class testi2 {
	static ModelAccessObject modelDAO = new ModelAccessObject();
	
	public static void main(String[] args) {
		
	SoftwareProfile p1 = modelDAO.readProfile("testi");
	System.out.println(p1.getProfileId() + " " + p1.getProfileName());
	
		
		//tämä ei toimi niinkuin haluaisi
		List<Gamedata> games = modelDAO.readGames();
		for(Gamedata game : games) {
			System.out.println(game.getGameid() + " " + game.getChampion() + " " + game.getKills() + " "+  game.getItem().getSlot2());
		}
	
	
		List<Item> items = modelDAO.readItems();
		for(Item item : items) {
			System.out.println(item.getItemid() + " " + item.getSlot1() + " " + item.getSlot2() + " " + item.getSlot3());
		}
		

		
		List<Gamedata> tulokset = modelDAO.readSpecificProfilesGames("testi");
		for(Gamedata tulos : tulokset) {
			System.out.println(tulos.getSoftwareProfile().getProfileName() + " " + tulos.getChampion() + " " + tulos.getItem().getSlot1());
		}
		
		List<Item> results = modelDAO.readGamesWithItems();
		for(Item result : results) {
			System.out.println(result.getSlot1() + " " + result.getGamedata().getChampion() + " " + result.getGamedata().getSoftwareProfile().getProfileName());
		}
		
		
		Additional additionalinfo = modelDAO.readAdditionalData(55);
		System.out.println(additionalinfo.getDamagedealt() + " " + additionalinfo.getDamagetaken() + " " + additionalinfo.getGold() + " " + additionalinfo.getDuration());
		
		Team teaminfo = modelDAO.readTeamComposition(45);
		System.out.println(teaminfo.getChampion1() + " " + teaminfo.getEnemyChampion3());
		
		Gamedata oneGame = modelDAO.readOneGame(55);
		System.out.println(oneGame.getIngameName() + " " + oneGame.getGameid() + " " + oneGame.getChampion() + " " + oneGame.getKills());
		
		boolean truth = modelDAO.checkGame(22);
		System.out.println(truth);
		
	}
}
