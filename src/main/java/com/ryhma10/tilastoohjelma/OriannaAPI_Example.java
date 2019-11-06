package com.ryhma10.tilastoohjelma;

import java.util.List;
import java.util.stream.Collectors;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;

public class OriannaAPI_Example {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("YOUR-API-KEY-HERE");
        Orianna.setDefaultPlatform(Platform.NORTH_AMERICA);

        Summoner fatalElement = Summoner.named("FatalElement").get();
        ChampionMasteries masteries = fatalElement.getChampionMasteries();
        List<ChampionMastery> goodWith = masteries.filter((ChampionMastery mastery) -> {
            assert mastery != null;
            return mastery.getLevel() >= 6;
        });

        List<String> names = goodWith.stream().map((ChampionMastery mastery) -> mastery.getChampion().getName()).collect(Collectors.toList());
        System.out.println("[" + String.join(", ", names) + "]");
    }
}