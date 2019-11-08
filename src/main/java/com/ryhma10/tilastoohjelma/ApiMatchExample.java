package com.ryhma10.tilastoohjelma;

import java.util.ArrayList;
import java.util.List;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;

public class ApiMatchExample {

public static void main(String[] args) throws InterruptedException {
		
        Orianna.setRiotAPIKey("RGAPI-369145d7-105b-41e5-9ae5-003962bb54a4");
        //Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        List<Summoner> summoners = Summoners.named("Daddy Chrollo").withRegion(Region.EUROPE_WEST).get();
        //System.out.println(summoners.get(0).getName());
        final String playerName = summoners.get(0).getName();
        
        /*
        Match match = Match.withId(id).withRegion(Region.EUROPE_NORTH_EAST).get();
        System.out.println(match.getDuration());
        System.out.println(match.getBlueTeam().getParticipants().get(4));
        List<Match> matches = Matches.withIds(2718292415L, 2718244702L).withRegion(Region.EUROPE_NORTH_EAST).get();
		*/


        MatchHistory history = MatchHistory.forSummoner(summoners.get(0)).get();
        ArrayList<Long> historyArrayList = new ArrayList<Long>();
        for (int i = 0; i < 1; i++) {
            Long matchId = (history.get(i).getId());
            //System.out.println(matchId.toString()); //matchId
            historyArrayList.add(matchId);
        }
        //System.out.println(historyArrayList);
        Match currentMatch;
        
        System.out.println(playerName);
        for (Long m : historyArrayList) {
            currentMatch = Match.withId(m).withRegion(Region.EUROPE_WEST).get();
            int j = 0;
            for (final Participant player : currentMatch.getParticipants()) {
            	if (currentMatch.getParticipants().get(j).getSummoner().getName().equals(playerName)) {
            		System.out.println("Match date: "+currentMatch.getCreationTime().toDate());
            		System.out.println("Game mode: "+currentMatch.getMode());
            		System.out.println("Game duration: "+currentMatch.getDuration().getStandardSeconds()+" sec");
            		System.out.println("Champion: "+player.getChampion().getName());
            		System.out.println("Kills: "+player.getStats().getKills());
                	System.out.println("Deaths: "+player.getStats().getDeaths());
                	System.out.println("Assists: "+player.getStats().getAssists());
                	int k = 0;
                	for(final Item items : currentMatch.getParticipants().get(j).getItems()) {
                		//items.getName();
                		if(k == 0) {
                			System.out.println("Items: - "+items.getName());
                		} else {
                			System.out.println("       - "+items.getName());
                		}
                		k++;
                	}
                	System.out.println("Gold earned: "+player.getStats().getGoldEarned());
                	if (player.getTeam().isWinner() == false) {
                		System.out.println("Result: Loss");
                	} else {
                		System.out.println("Result: Win");
                	}
                	System.out.println("- - - - -");
            	}
            	j++;
            }
            Thread.sleep(3000);
        }
        
        
        
        
        /*
        for (Long m : historyArrayList) {
            currentMatch = Match.withId(m).withRegion(Region.EUROPE_NORTH_EAST).get();
            //System.out.println(currentMatch);
            for (final Participant player : currentMatch.getParticipants()) {
            	if (currentMatch.getParticipants().get(0).getSummoner().getName().equals(playerName)) {
            		System.out.println(playerName);
            		System.out.println("Champion: "+player.getChampion().getName());
            		System.out.println("Kills: "+player.getStats().getKills());
                	System.out.println("Deaths: "+player.getStats().getDeaths());
                	System.out.println("Assists: "+player.getStats().getAssists());
                	System.out.println("- - - - -");
            	}
            	
            }
            Thread.sleep(1500);
        }*/
        
        
        //List<MatchHistory> histories = MatchHistories.forSummoners(summoners).get();

        /*MatchHistory recentHistory = MatchHistory.forSummoner(summoners.get(0)).fromRecentMatches().get();
        List<MatchHistory> recentHistories = MatchHistories.forSummoners(summoners).fromRecentMatches().get();

        Timeline timeline = Timeline.withId(2718292415L).withRegion(Region.NORTH_AMERICA).get();
        List<Timeline> timelines = Timelines.withIds(2718292415L, 2718244702L).withRegion(Region.NORTH_AMERICA).get();*/
    }
}
	
