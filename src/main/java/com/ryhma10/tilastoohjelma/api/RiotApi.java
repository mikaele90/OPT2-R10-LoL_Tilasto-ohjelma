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

public class RiotApi {
	
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
	
	Match currentMatch;
	public static String wantedPlayer;
	static String playerName, playerRank, matchDate, playerTeam, championPlayed, otherPlayers, itemName, matchResult, queueType;
	static long matchId, matchDuration, matchKills, matchDeaths, matchAssists, damageDealt, damageTaken, goldEarned;
	static String blueTeam = "BLUE";
	static String redTeam = "RED";
	static String emptyObject = "This is an empty object";
	static int historySize;
	
	ApiData[][] matchArray;
	ApiKey key = new ApiKey();
	
	public void setWantedPlayer(String name) {
		wantedPlayer = name;
	}
	
	public void setMatchListSize(int size) {
		historySize = size;
	}
	
	public int getMatchListSize() {
		return historySize;
	}
	
	public void setKey() {
		Orianna.setRiotAPIKey(key.getKey());
	}
    
	public ApiData[][] getMatchHistory() throws InterruptedException {
		List<Summoner> summoners = Summoners.named(wantedPlayer).withRegion(Region.EUROPE_NORTH_EAST).get();
		playerName = summoners.get(0).getName();
		MatchHistory history = MatchHistory.forSummoner(summoners.get(0)).get();
        ArrayList<Long> historyArrayList = new ArrayList<Long>();
        matchArray = new ApiData[historySize][51];
        for (int i = 0; i < historySize; i++) {
            Long matchId = (history.get(i).getId());
            historyArrayList.add(matchId);
        }
        int l = 0; // Match
        for (Long m : historyArrayList) {
            currentMatch = Match.withId(m).withRegion(Region.EUROPE_NORTH_EAST).get();
            int participants = 0;
            int k = 20; // Items
            int n = 30; // Blue team
            int o = 40; // Red team
            for (final Participant player : currentMatch.getParticipants()) {
            	if (currentMatch.getParticipants().get(participants).getSummoner().getName().equals(playerName)) {
            		if(player.getSummoner().getLeague(Queue.RANKED_SOLO) == null) {
            			playerRank = "No Rank";
            		} else {
            			playerRank = player.getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getTier()+" "+player.getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getDivision();
            		}
            		//System.out.println(player.getStats());
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
                	for (int empty = 14; empty < 20; empty++) {
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
        return matchArray;
	}
}
