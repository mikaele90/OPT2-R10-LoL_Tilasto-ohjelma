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
			// 14 = Creep Score
			// 15 = Wards Placed
			// 16-19 = Empty
			// 20-29 = Items, leftover slots Empty
			// 30-39 = Blue Team Champions, leftover slots Empty
			// 40-49 = Red Team Champions, leftover slots Empty
			// 50 = QueueType
	
	int size;
	static String queueType = "RANKED_SOLO";
	String playerName, playerId;
	int historySize;
	
	
	/**
	 * Method to set player name
	 * @param name
	 */
	public void setPlayerName(String name) {
		if(name == null) {
			System.out.println("Name needed");
		} else {
			playerName = name;
		} 
	}
	
	
	/**
	 * Method to set history size
	 * @param history
	 */
	public void setHistorySize(int history) {
		if (history < 1) {
			System.out.println("We want to find at least one match...");
		} else {
			historySize = history;
		}
	}

	
	/**
	 * Method to get player's EUW server match history and match information through RiotAPI by setting the correct API key,
	 * setting the wanted player's name, setting how many matches to get, executing RiotApi class'
	 * getMatchHistory() method and return the received information as a 2d array
	 * @return data
	 */
	public ApiData[][] getDataEUW() {
		ApiData[][] data = null;
		RiotApiEUW api = new RiotApiEUW();
		api.setKey();
		api.setWantedPlayer(playerName);
		api.setMatchListSize(historySize);
		size = api.getMatchListSize();
		try {
			data = api.getMatchHistory();
			playerId = api.getPlayerId();
			if(api.getPlayerId() == null) {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * Method to get player's EUNE server match history and match information through RiotAPI by setting the correct API key,
	 * setting the wanted player's name, setting how many matches to get, executing RiotApi class'
	 * getMatchHistory() method and return the received information as a 2d array
	 * @return data
	 */
	public ApiData[][] getDataEUNE() {
		ApiData[][] data = null;
		RiotApiEUNE api = new RiotApiEUNE();
		api.setKey();
		api.setWantedPlayer(playerName);
		api.setMatchListSize(historySize);
		size = api.getMatchListSize();
		try {
			data = api.getMatchHistory();
			playerId = api.getPlayerId();
			if(api.getPlayerId() == null) {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * Method to get the size of the list of matches
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	
	/**
	 * Method to get and return the player's name from each match
	 * @param data
	 * @param matchIndex
	 * @return playerName
	 */
	public String getPlayerName(ApiData[][] data, int matchIndex) {
		String playerName = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof PlayerName) {
				playerName = ((PlayerName)data[matchIndex][z]).getPlayerName();
			}
		}
		return playerName;
	}
	
	
	/**
	 * Method to get and return player id
	 * @return playerId
	 */
	public String getPlayerId() {
		return playerId;
	}
	
	
	/**
	 * Method to get and return the player's rank from each match
	 * @param data
	 * @param matchIndex
	 * @return playerRank
	 */
	public String getPlayerRank(ApiData[][] data, int matchIndex) {
		String playerRank = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof PlayerRank) {
				playerRank = ((PlayerRank)data[matchIndex][z]).getPlayerRank();
			}
		}
		return playerRank;
	}
	
	
	/**
	 * Method to get and return the match id of each match
	 * @param data
	 * @param matchIndex
	 * @return matchId
	 */
	public long getMatchId(ApiData[][] data, int matchIndex) {
		long matchId = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchId) {
				matchId = ((MatchId)data[matchIndex][z]).getMatchId();
			}
		}
		return matchId;
	}
	
	
	/**
	 * Method to get and return the date of each match
	 * @param data
	 * @param matchIndex
	 * @return matchDate
	 */
	public String getMatchDate(ApiData[][] data, int matchIndex) {
		String matchDate = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDate) {
				matchDate = ((MatchDate)data[matchIndex][z]).getMatchDate();
			}
		}
		return matchDate;
	}
	
	
	/**
	 * Method to get and return the duration of each match
	 * @param data
	 * @param matchIndex
	 * @return matchDuration
	 */
	public long getMatchDuration(ApiData[][] data, int matchIndex) {
		long matchDuration = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDuration) {
				matchDuration = ((MatchDuration)data[matchIndex][z]).getMatchDuration();
			}
		}
		return matchDuration;
	}
	
	
	/**
	 * Method to get and return the champion's name the player played as each match
	 * @param data
	 * @param matchIndex
	 * @return championPlayed
	 */
	public String getChampionPlayed(ApiData[][] data, int matchIndex) {
		String championPlayed = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof ChampionPlayed) {
				championPlayed = ((ChampionPlayed)data[matchIndex][z]).getChampionPlayed();
			}
		}
		return championPlayed;
	}
	
	
	/**
	 * Method to get and return the amount of kills the player got each match
	 * @param data
	 * @param matchIndex
	 * @return matchKills
	 */
	public long getMatchKills(ApiData[][] data, int matchIndex) {
		long matchKills = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchKills) {
				matchKills = ((MatchKills)data[matchIndex][z]).getMatchKills();
			}
		}
		return matchKills;
	}
	
	
	/**
	 * Method to get and return the amount of deaths from each match
	 * @param data
	 * @param matchIndex
	 * @return matchDeaths
	 */
	public long getMatchDeaths(ApiData[][] data, int matchIndex) {
		long matchDeaths = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchDeaths) {
				matchDeaths = ((MatchDeaths)data[matchIndex][z]).getMatchDeaths();
			}
		}
		return matchDeaths;
	}
	
	
	/**
	 * Method to get and return the amount of assists from each match
	 * @param data
	 * @param matchIndex
	 * @return matchAssists
	 */
	public long getMatchAssists(ApiData[][] data, int matchIndex) {
		long matchAssists = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchAssists) {
				matchAssists = ((MatchAssists)data[matchIndex][z]).getMatchAssists();
			}
		}
		return matchAssists;
	}
	
	
	/**
	 * Method to get and return the amount of damage the player dealt each match
	 * @param data
	 * @param matchIndex
	 * @return damageDealt
	 */
	public long getDamageDealt(ApiData[][] data, int matchIndex) {
		long damageDealt = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof DamageDealt) {
				damageDealt = ((DamageDealt)data[matchIndex][z]).getDamageDealt();
			}
		}
		return damageDealt;
	}
	
	
	/**
	 * Method to get and return the amount of damage the player took each match
	 * @param data
	 * @param matchIndex
	 * @return damageTaken
	 */
	public long getDamageTaken(ApiData[][] data, int matchIndex) {
		long damageTaken = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof DamageTaken) {
				damageTaken = ((DamageTaken)data[matchIndex][z]).getDamageTaken();
			}
		}
		return damageTaken;
	}
	
	
	/**
	 * Method to get and return the amount of gold the player earned each match
	 * @param data
	 * @param matchIndex
	 * @return goldEarned
	 */
	public long getGoldEarned(ApiData[][] data, int matchIndex) {
		long goldEarned = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof GoldEarned) {
				goldEarned = ((GoldEarned)data[matchIndex][z]).getGoldEarned();
			}
		}
		return goldEarned;
	}
	
	
	/**
	 * Method to get and return the result of each match
	 * @param data
	 * @param matchIndex
	 * @return matchResult
	 */
	public String getMatchResult(ApiData[][] data, int matchIndex) {
		String matchResult = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof MatchResult) {
				matchResult = ((MatchResult)data[matchIndex][z]).getMatchResult();
			}
		}
		return matchResult;
	}
	
	
	/**
	 * Method to get and return the player's team color from each match
	 * @param data
	 * @param matchIndex
	 * @return teamColor
	 */
	public String getTeamColor(ApiData[][] data, int matchIndex) {
		String teamColor = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof TeamColor) {
				teamColor = ((TeamColor)data[matchIndex][z]).getTeamColor();
			}
		}
		return teamColor;
	}
	
	
	/**
	 * Method to get and return the player's creep score from each match
	 * @param data
	 * @param matchIndex
	 * @return creepScore
	 */
	public long getCreepScore(ApiData[][] data, int matchIndex) {
		long creepScore = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof CreepScore) {
				creepScore = ((CreepScore)data[matchIndex][z]).getCreepScore();
			}
		}
		return creepScore;
	}
	
	
	/**
	 * Method to get and return the amount of wards the player placed each match
	 * @param data
	 * @param matchIndex
	 * @return wardsPlaced
	 */
	public long getWardsPlaced(ApiData[][] data, int matchIndex) {
		long wardsPlaced = 0;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof WardsPlaced) {
				wardsPlaced = ((WardsPlaced)data[matchIndex][z]).getWardsPlaced();
			}
		}
		return wardsPlaced;
	}
	
	
	/**
	 * Method to get and return the player's position from each match
	 * @param data
	 * @param matchIndex
	 * @return position
	 */
	public String getPosition(ApiData[][] data, int matchIndex) {
		String position = null;
		for(int z = 0; z < data[matchIndex].length; z++) {
			if (data[matchIndex][z] instanceof Position) {
				position = ((Position)data[matchIndex][z]).getPosition();
			}
		}
		return position;
	}
	
	
	/**
	 * Method to get and return a list of items the player had each match
	 * @param data
	 * @param matchIndex
	 * @return itemList
	 */
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
	
	
	/**
	 * Method to get and return a list of champions the blue team had each match
	 * @param data
	 * @param matchIndex
	 * @return blueTeam
	 */
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
	
	
	/**
	 * Method to get and return a list of champions the red team had each match
	 * @param data
	 * @param matchIndex
	 * @return redTeam
	 */
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
	
	
	/**
	 * Method to get and return the game mode of each match
	 * @param data
	 * @param matchIndex
	 * @return queueType
	 */
	public String getQueueType(ApiData[][] data, int matchIndex) {
		String queueType = null;
		queueType = ((QueueType)data[matchIndex][50]).getQueueType();
		return queueType;
	}
}
