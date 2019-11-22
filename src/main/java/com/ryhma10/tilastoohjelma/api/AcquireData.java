package com.ryhma10.tilastoohjelma.api;

public class AcquireData {
	
	// RiotApi-luokan getMatchHistory()-metodin palauttavan listan elementtien sijainnit:
	
			// 0 = PlayerName
			// 1 = PlayerRank
			// 2 = MatchId
			// 3 = MatchDate
			// 4 = MatchDuration
			// 5 = ChampionPlayed
			// 6 = MatchKills
			// 7 = MatchDeaths
			// 8 = MatchAssists
			// 9 = DamageDealt
			// 10 = DamageTaken
			// 11 = GoldEarned
			// 12 = MatchResult
			// 13 = Player's Team Color
			// 14-19 = Empty
			// 20-29 = Items, leftover slots Empty
			// 30-39 = Blue Team Champions, leftover slots Empty
			// 40-49 = Red Team Champions, leftover slots Empty
			// 50 = QueueType
	
	int size;
	static String queueType = "RANKED_SOLO";
	String playerName;
	int historySize;
	
	public void setPlayerName(String name) {
		if(name == null) {
			System.out.println("Name needed");
		} else {
			playerName = name;
		} 
	}
	
	public void setHistorySize(int history) {
		if (history < 1) {
			System.out.println("We want to find at least one match...");
		} else {
			historySize = history;
		}
	}

	
	public ApiData[][] getData() {
		ApiData[][] data = null;
		RiotApi api = new RiotApi();
		api.setKey();
		api.setWantedPlayer(playerName);
		api.setMatchListSize(historySize);
		size = api.getMatchListSize();
		try {
			data = api.getMatchHistory();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public String getPlayerName(ApiData[][] data, int matchIndex) {
		String playerName = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof PlayerName) {
				playerName = ((PlayerName)data[matchIndex][z]).getPlayerName();
			}
		}
		return playerName;
	}
	
	
	public String getPlayerRank(ApiData[][] data, int matchIndex) {
		String playerRank = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof PlayerRank) {
				playerRank = ((PlayerRank)data[matchIndex][z]).getPlayerRank();
			}
		}
		return playerRank;
	}
	
	
	public long getMatchId(ApiData[][] data, int matchIndex) {
		long matchId = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchId) {
				matchId = ((MatchId)data[matchIndex][z]).getMatchId();
			}
		}
		return matchId;
	}
	
	
	public String getMatchDate(ApiData[][] data, int matchIndex) {
		String matchDate = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDate) {
				matchDate = ((MatchDate)data[matchIndex][z]).getMatchDate();
			}
		}
		return matchDate;
	}
	
	
	public long getMatchDuration(ApiData[][] data, int matchIndex) {
		long matchDuration = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDuration) {
				matchDuration = ((MatchDuration)data[matchIndex][z]).getMatchDuration();
			}
		}
		return matchDuration;
	}
	
	
	public String getChampionPlayed(ApiData[][] data, int matchIndex) {
		String championPlayed = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof ChampionPlayed) {
				championPlayed = ((ChampionPlayed)data[matchIndex][z]).getChampionPlayed();
			}
		}
		return championPlayed;
	}
	
	
	public long getMatchKills(ApiData[][] data, int matchIndex) {
		long matchKills = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchKills) {
				matchKills = ((MatchKills)data[matchIndex][z]).getMatchKills();
			}
		}
		return matchKills;
	}
	
	
	public long getMatchDeaths(ApiData[][] data, int matchIndex) {
		long matchDeaths = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDeaths) {
				matchDeaths = ((MatchDeaths)data[matchIndex][z]).getMatchDeaths();
			}
		}
		return matchDeaths;
	}
	
	
	public long getMatchAssists(ApiData[][] data, int matchIndex) {
		long matchAssists = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchAssists) {
				matchAssists = ((MatchAssists)data[matchIndex][z]).getMatchAssists();
			}
		}
		return matchAssists;
	}
	
	
	public long getDamageDealt(ApiData[][] data, int matchIndex) {
		long damageDealt = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof DamageDealt) {
				damageDealt = ((DamageDealt)data[matchIndex][z]).getDamageDealt();
			}
		}
		return damageDealt;
	}
	
	
	public long getDamageTaken(ApiData[][] data, int matchIndex) {
		long damageTaken = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof DamageTaken) {
				damageTaken = ((DamageTaken)data[matchIndex][z]).getDamageTaken();
			}
		}
		return damageTaken;
	}
	
	
	public long getGoldEarned(ApiData[][] data, int matchIndex) {
		long goldEarned = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof GoldEarned) {
				goldEarned = ((GoldEarned)data[matchIndex][z]).getGoldEarned();
			}
		}
		return goldEarned;
	}
	
	
	public String getMatchResult(ApiData[][] data, int matchIndex) {
		String matchResult = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchResult) {
				matchResult = ((MatchResult)data[matchIndex][z]).getMatchResult();
			}
		}
		return matchResult;
	}
	
	
	public String getTeamColor(ApiData[][] data, int matchIndex) {
		String teamColor = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof TeamColor) {
				teamColor = ((TeamColor)data[matchIndex][z]).getTeamColor();
			}
		}
		return teamColor;
	}
	
	
	public String[] getItemNames(ApiData[][] data, int matchIndex) {
		String[] itemList = new String[7];
		String itemName = null;
		int x = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof ItemName) {
				itemName = ((ItemName)data[matchIndex][z]).getItemName();
				itemList[x] = itemName;
				x++;
			}
		}
		return itemList;
	}
	
	
	public String[] getBlueTeamChampions(ApiData[][] data, int matchIndex) {
		String[] blueTeam = new String[5];
		String championName = null;
		int x = 0;
		for(int z = 30; z < 40; z++) {
			if (data[matchIndex][z] instanceof OtherPlayers) {
				championName = ((OtherPlayers)data[matchIndex][z]).getOtherPlayers();
				blueTeam[x] = championName;
				x++;
			}
		}
		return blueTeam;
	}
	
	
	public String[] getRedTeamChampions(ApiData[][] data, int matchIndex) {
		String[] redTeam = new String[5];
		String championName = null;
		int x = 0;
		for(int z = 40; z < 50; z++) {
			if (data[matchIndex][z] instanceof OtherPlayers) {
				championName = ((OtherPlayers)data[matchIndex][z]).getOtherPlayers();
				redTeam[x] = championName;
				x++;
			}
		}
		return redTeam;
	}
	
	
	public String getQueueType(ApiData[][] data, int matchIndex) {
		String queueType = null;
		queueType = ((QueueType)data[matchIndex][50]).getQueueType();
		return queueType;
	}
}
