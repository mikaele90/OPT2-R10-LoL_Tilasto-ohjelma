package com.ryhma10.tilastoohjelma.api;

import java.util.ArrayList;
import java.util.List;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;

public class RiotApiEUW {
	
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
				// 16 = Position
				// 17-19 = Empty
				// 20-29 = Items, leftover slots Empty
				// 30-39 = Blue Team Champions, leftover slots Empty
				// 40-49 = Red Team Champions, leftover slots Empty
				// 50 = QueueType
	
	
	Match currentMatch;
	public static String wantedPlayer;
	static String playerName, playerRank, matchDate, playerTeam, championPlayed, otherPlayers, itemName, matchResult, queueType, playedPosition, playerId;
	static long matchId, matchDuration, matchKills, matchDeaths, matchAssists, damageDealt, damageTaken, goldEarned, creepScore, wardsPlaced;
	static String blueTeam = "BLUE";
	static String redTeam = "RED";
	static String emptyObject = "Empty";
	static int historySize;
	
	ApiData[][] matchArray;
	ApiKey key = new ApiKey();
	
	
	/**
	 * Method to set the player name whose match history the user wants to search
	 * @param name
	 */
	public void setWantedPlayer(String name) {
		wantedPlayer = name;
	}
	
	
	/**
	 * Method to set how many matches to get from user's match history
	 * @param size
	 */
	public void setMatchListSize(int size) {
		historySize = size;
	}
	
	
	/**
	 * Method to return the size of the match list
	 * @return historySize
	 */
	public int getMatchListSize() {
		return historySize;
	}
	
	
	/**
	 * Method to return the player id
	 * @return playerId
	 */
	public String getPlayerId() {
		return playerId;
	}
	
	
	/**
	 * Method to set the correct API Key for Orianna Framework
	 */
	public void setKey() {
		Orianna.setRiotAPIKey(key.getKey());
	}
    
	
	/**
	 * Method to search the wanted players match history, filtering relevant information from each match
	 * and returning the match list as a 2d array
	 * @return matchArray
	 * @throws InterruptedException
	 */
	public ApiData[][] getMatchHistory() throws InterruptedException {
		List<Summoner> summoners = Summoners.named(wantedPlayer).withRegion(Region.EUROPE_WEST).get();
		playerName = summoners.get(0).getName();
		playerId = summoners.get(0).getAccountId();
		if(summoners.get(0).getAccountId() != null) {
		MatchHistory history = MatchHistory.forSummoner(summoners.get(0)).get();
        ArrayList<Long> historyArrayList = new ArrayList<Long>();
        matchArray = new ApiData[historySize][51];
        for (int i = 0; i < historySize; i++) {
            Long matchId = (history.get(i).getId());
            historyArrayList.add(matchId);
        }
        int l = 0; // Match pointer
        for (Long m : historyArrayList) {
            currentMatch = Match.withId(m).withRegion(Region.EUROPE_WEST).get();
            int participants = 0;
            int k = 20; // Items pointer
            int n = 30; // Blue team pointer
            int o = 40; // Red team pointer
            for (final Participant player : currentMatch.getParticipants()) {
            	if (currentMatch.getParticipants().get(participants).getSummoner().getName().equals(playerName)) {
            		if(player.getSummoner().getLeague(Queue.RANKED_SOLO) == null) {
            			playerRank = "No Rank";
            		} else {
            			playerRank = player.getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getTier()+" "+player.getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getDivision();
            		}
            		matchId = currentMatch.getId();
            		matchDate = currentMatch.getCreationTime().toDate().toString();
            		matchDuration = currentMatch.getDuration().getStandardSeconds();
            		championPlayed = player.getChampion().getName();
            		matchKills = player.getStats().getKills();
                	matchDeaths = player.getStats().getDeaths();
                	matchAssists = player.getStats().getAssists();
                	damageDealt = player.getStats().getDamageDealtToChampions();
                	damageTaken = player.getStats().getDamageTaken();
                	goldEarned = player.getStats().getGoldEarned();
                	playerTeam = player.getTeam().getSide().name();
                	creepScore = player.getStats().getCreepScore();
                	wardsPlaced = player.getStats().getWardsPlaced();
                	playedPosition = player.getLane().toString();
                	if (player.getTeam().isWinner() == false) {
                		matchResult = "Loss";
                	} else {
                		matchResult = "Win";
                	}
                	matchArray[l][0] = new PlayerName(playerName);
                	//System.out.println(((PlayerName) matchArray[l][0]).getPlayerName());
                	Thread.sleep(100);
                	matchArray[l][1] = new PlayerRank(playerRank);
                	//System.out.println(((PlayerRank) matchArray[l][1]).getPlayerRank());
                	Thread.sleep(100);      	
                	matchArray[l][2] = new MatchId(matchId);
                	//System.out.println(((MatchId) matchArray[l][2]).getMatchId());
                	Thread.sleep(100);                	
                	matchArray[l][3] = new MatchDate(matchDate);
                	//System.out.println(((MatchDate) matchArray[l][3]).getMatchDate());
                	Thread.sleep(100);               	
                	matchArray[l][4] = new MatchDuration(matchDuration);
                	//System.out.println(((MatchDuration) matchArray[l][4]).getMatchDuration());
                	Thread.sleep(100);                	
                	matchArray[l][5] = new ChampionPlayed(championPlayed);
                	//System.out.println(((ChampionPlayed) matchArray[l][5]).getChampionPlayed());
                	Thread.sleep(100);              	
                	matchArray[l][6] = new MatchKills(matchKills);
                	//System.out.println(((MatchKills) matchArray[l][6]).getMatchKills());
                	Thread.sleep(100);               	
                	matchArray[l][7] = new MatchDeaths(matchDeaths);
                	//System.out.println(((MatchDeaths) matchArray[l][7]).getMatchDeaths());
                	Thread.sleep(100);               	
                	matchArray[l][8] = new MatchAssists(matchAssists);
                	//System.out.println(((MatchAssists) matchArray[l][8]).getMatchAssists());
                	Thread.sleep(100);             	
                	matchArray[l][9] = new DamageDealt(damageDealt);
                	//System.out.println(((DamageDealt) matchArray[l][9]).getDamageDealt());
                	Thread.sleep(100);                	
                	matchArray[l][10] = new DamageTaken(damageTaken);
                	//System.out.println(((DamageTaken) matchArray[l][10]).getDamageTaken());
                	Thread.sleep(100);                	
                	matchArray[l][11] = new GoldEarned(goldEarned);
                	//System.out.println(((GoldEarned) matchArray[l][11]).getGoldEarned());
                	Thread.sleep(100);               	
                	matchArray[l][12] = new MatchResult(matchResult);
                	//System.out.println(((MatchResult) matchArray[l][12]).getMatchResult());
                	Thread.sleep(100);               	
                	matchArray[l][13] = new TeamColor(playerTeam);
                	//System.out.println(((TeamColor) matchArray[l][13]).getTeamColor());
                	Thread.sleep(100);
                	matchArray[l][14] = new CreepScore(creepScore);
                	//System.out.println(((CreepScore) matchArray[l][14]).getCreepScore());
                	Thread.sleep(100);
                	matchArray[l][15] = new WardsPlaced(wardsPlaced);
                	//System.out.println(((WardsPlaced) matchArray[l][15]).getWardsPlaced());
                	Thread.sleep(100);
                	matchArray[l][16] = new Position(playedPosition);
                	//System.out.println(((Position) matchArray[l][16]).getPosition());
                	Thread.sleep(100);
                	for (int empty = 17; empty < 20; empty++) {
                		matchArray[l][empty] = new EmptyObject(emptyObject);
                	}
                	for(final Item items : currentMatch.getParticipants().get(participants).getItems()) {
                		if(items.getName() == null) {
                			matchArray[l][k] = new EmptyObject(emptyObject);
                		} else {
                			itemName = items.getName();
                    		matchArray[l][k] = new ItemName(itemName);
                    		//System.out.println(((ItemName) matchArray[l][k]).getItemName());
                		}
                		k++;
                		Thread.sleep(100);
                	}
            	} else {
            		if (player.getTeam().getSide().name() == blueTeam) {
            			otherPlayers = player.getChampion().getName();
                		matchArray[l][n] = new OtherPlayers(otherPlayers);
                		//System.out.println(((OtherPlayers) matchArray[l][n]).getOtherPlayers());
                		n++;
            		} else {
            			otherPlayers = player.getChampion().getName();
                		matchArray[l][o] = new OtherPlayers(otherPlayers);
                		//System.out.println(((OtherPlayers) matchArray[l][o]).getOtherPlayers());
                		o++;
            		}
            		Thread.sleep(100);
            	}
            	participants++;
            }
            queueType = currentMatch.getQueue().name();
            matchArray[l][50] = new QueueType(queueType);
            //System.out.println(((QueueType) matchArray[l][50]).getQueueType()); 
            //System.out.println("- - - - - - - - - - - -");
            for(int q = 0; q < matchArray.length; q++) {
            	for(int w = 0; w < matchArray[q].length; w++) {
            		if(matchArray[q][w] == null) {
            			matchArray[q][w] = new EmptyObject(emptyObject);
            		}
            		//System.out.println(matchArray[q][w].toString());
            	}
            }
            l++;
            Thread.sleep(3000);
		}
        System.out.println("Match history acquired!");
		} else {

        }
        return matchArray;
	}
}
