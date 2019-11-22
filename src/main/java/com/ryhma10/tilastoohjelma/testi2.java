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
		
		
		//toimii
		List<Additional> jahas = modelDAO.readAdditionalData(55);
		for(Additional result : jahas) {
			System.out.println(result.getDamagedealt() + " " + result.getDamagetaken() + " " + result.getGold() + " " + result.getDuration());
		}
		
		List<Team> kaipa = modelDAO.readTeamComposition(45);
		for(Team result : kaipa) {
			System.out.println(result.getChampion1() + " " + result.getEnemyChampion3());
		}
		
		Gamedata oneGame = modelDAO.readOneGame(55);
		System.out.println(oneGame.getIngameName() + " " + oneGame.getGameid() + " " + oneGame.getChampion() + " " + oneGame.getKills());
		
	}
}
