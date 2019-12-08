package com.ryhma10.tilastoohjelma.model;

import com.ryhma10.tilastoohjelma.api.AcquireData;
import com.ryhma10.tilastoohjelma.api.ApiData;

import java.util.ArrayList;

public class FromApiToDatabase {
		
		
		Gamedata gamedata;
		Item item;
		Team team;
		Additional additional;
		String playerName;
		ApiData[][] array;

		public FromApiToDatabase() {
			//Constructor
		}
		
		/**
		 * Method to get player's match history from server and store it in database
		 */
		public void storeData(ArrayList<Long> matchIdsToStore) {
			ModelAccessObject mao = new ModelAccessObject();
			AcquireData data = new AcquireData();
			data.setPlayerName(playerName);
			data.setHistorySize(50);
			array = data.getData(matchIdsToStore);
			if (data.getPlayerId() == null) {
				System.out.println("Player not found");
			} else {
				int counter = 0;
				for(int i = 0; i < data.getSize(); i++) {
					gamedata = new Gamedata(data.getMatchId(array, i), data.getPlayerName(array, i), 
							data.getChampionPlayed(array, i), data.getMatchKills(array, i), data.getMatchDeaths(array, i), 
							data.getMatchAssists(array, i), data.getMatchResult(array, i), 
							data.getPosition(array, i), data.getPlayerRank(array, i));
					additional = new Additional(data.getDamageDealt(array, i), data.getDamageTaken(array, i), 
							data.getQueueType(array, i), data.getGoldEarned(array, i), data.getMatchDuration(array, i), 
							data.getMatchDate(array, i), data.getWardsPlaced(array, i), data.getCreepScore(array, i));
					String[] items = data.getItemNames(array, i);
					item = new Item(items[0], items[1], items[2], items[3], items[4], items[5], items[6]);
					String[] blue = data.getBlueTeamChampions(array, i);
					String[] red = data.getRedTeamChampions(array, i);
					if (data.getTeamColor(array, i).equals("BLUE")) {
						team = new Team(blue[0], blue[1], blue[2], blue[3], red[0], red[1], red[2], red[3], red[4]);
					}
					else {
						team = new Team(red[0], red[1], red[2], red[3], blue[0], blue[1], blue[2], blue[3], blue[4]);
					}
					if (!mao.checkGame(data.getMatchId(array, i))) {
						mao.createGamedata("testi", gamedata, item, team, additional);
						counter ++;
					}
				}
				System.out.println(counter+" matches added to database");
			}
			}	
		}

