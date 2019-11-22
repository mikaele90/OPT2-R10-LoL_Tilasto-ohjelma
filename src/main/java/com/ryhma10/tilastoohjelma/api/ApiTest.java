package com.ryhma10.tilastoohjelma.api;

public class ApiTest {
	
	public static void main(String[] args) {
		
		AcquireData data = new AcquireData();
		data.setPlayerName("Syöpäkakka");
		data.setHistorySize(5);
		ApiData[][] array = data.getData();
		for(int i = 0; i < data.getSize(); i++) {
			System.out.println("Name: "+data.getPlayerName(array, i));
			System.out.println("Rank: "+data.getPlayerRank(array, i));
			System.out.println("Match ID: "+data.getMatchId(array, i));
			System.out.println("Match Date: "+data.getMatchDate(array, i));
			System.out.println("Match Duration: "+data.getMatchDuration(array, i));
			System.out.println("Champion: "+data.getChampionPlayed(array, i));
			System.out.println("Kills: "+data.getMatchKills(array, i));
			System.out.println("Deaths: "+data.getMatchDeaths(array, i));
			System.out.println("Assists: "+data.getMatchAssists(array, i));
			System.out.println("Damage Dealt: "+data.getDamageDealt(array, i));
			System.out.println("Damage Taken: "+data.getDamageTaken(array, i));
			System.out.println("Gold: "+data.getGoldEarned(array, i));
			System.out.println("Match Result: "+data.getMatchResult(array, i));
			System.out.println("Team: "+data.getTeamColor(array, i));
			String[] items = data.getItemNames(array, i);
			System.out.println("Items: ");
			for (String item : items) {
				if (item != null) {
					System.out.println("-"+item);
				}
			}
			String[] blueChampions = data.getBlueTeamChampions(array, i);
			System.out.println("Blue Team: ");
			for (String champion : blueChampions) {
				if (champion != null) {
					System.out.println("-"+champion);
				}
			}
			String[] redChampions = data.getRedTeamChampions(array, i);
			System.out.println("Red Team: ");
			for (String champion : redChampions) {
				if (champion != null) {
					System.out.println("-"+champion);
				}
			}
			System.out.println("Game Type: "+data.getQueueType(array, i));
			System.out.println("- - - - - - - - - - -");
		}
		
	}

}
