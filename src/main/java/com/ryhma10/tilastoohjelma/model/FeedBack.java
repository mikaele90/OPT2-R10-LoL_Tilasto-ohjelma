package com.ryhma10.tilastoohjelma.model;

public class FeedBack {
	
	//Esitellään tarvittavat muuttujat.
	long kills, assists, deaths, cs, gold, wards, riotid;
	double kda, gamelength, cspermin, dmgdealt, dmgpermin, gpm;
	String rank,winstreakscore, champion, winlose, result, KDAevaluation,csfeedback, kdafeedback,wardfeedback, DMGevaluation,
	wardevaluation, csevaluation,dmgevaluation, champion1, champion2, champion3, champion4, enemy1,
	enemy2, enemy3,enemy4,enemy5;
	boolean winloss;
	
	
	//Iron statistiikat viime kuukauden keskiarvoilla
	double ironkda = 1.64;
	int ironcs = 81; 
	int irongold = 9485;
	int ironwards = 1;
	
	//Bronze statistiikat viime kuukauden keskiarvoilla
	double bronzekda = 2.02;
	int bronzecs = 90; 
	int bronzegold = 10182;
	int bronzewards = 1;
	
	//Silver statistiikat viime kuukauden keskiarvoilla
	double silverkda = 2.31;
	int silvercs = 101; 
	int silvergold = 10720;
	int silverwards = 1;
	
	//Gold statistiikat viime kuukauden keskiarvoilla
	double goldkda = 2.31;
	int goldcs = 101; 
	int goldgold = 10720;
	int goldwards = 1;
		
	//Platinum statistiikat viime kuukauden keskiarvoilla
	double platkda = 2.31;
	int platcs = 101; 
	int platgold = 10720;
	int platwards = 1;
	
	//Diamond statistiikat viime kuukauden keskiarvoilla
	double diakda = 2.31;
	int diacs = 101; 
	int diagold = 10720;
	int diawards = 1;
	
	//Master statistiikat viime kuukauden keskiarvoilla
	double masterkda = 2.31;
	int mastercs = 101; 
	int mastergold = 10720;
	int masterwards = 1;
			
	//Grandmaster statistiikat viime kuukauden keskiarvoilla
	double gmasterkda = 2.31;
	int gmastercs = 101; 
	int gmastergold = 10720;
	int gmasterwards = 1;
			
	//Challenger statistiikat viime kuukauden keskiarvoilla
	double challkda = 2.31;
	int challcs = 101; 
	int challgold = 10720;
	int challwards = 1;
		
	
	
	
	/**
	 * Constructor to analyse the data gathered from the database.
	 * @param riotid riotid is placed into the constructor to tell the constructor which specific game  it needs to get information from
	 */
	public FeedBack (long riotid) {
		this.riotid = riotid;
		ModelAccessObject mao = new ModelAccessObject();
		Gamedata stats = mao.readOneGame(riotid);
		Additional morestats = mao.readAdditionalData(riotid);
		Team champions = mao.readTeamComposition(riotid);
		
		kills = stats.getKills();
		assists = stats.getAssist();
		deaths = stats.getDeaths();
		kda = (kills+assists)/deaths;
		champion = stats.getChampion();
		winlose = stats.getWinlose();
		rank = stats.getRank();
		wards = morestats.getWardsScore();
		dmgdealt = morestats.getDamagedealt();
		gold = morestats.getGold();
		gamelength = morestats.getDuration();
		gpm = gold/gamelength;
		cs = morestats.getCreepScore();
		cspermin = cs/gamelength;
		dmgpermin = dmgdealt/gamelength;
		champion1 = champions.getChampion1();
		champion2 = champions.getChampion2();
		champion3 = champions.getChampion3();
		champion4 = champions.getChampion4();
		enemy1 = champions.getEnemyChampion1();
		enemy2 = champions.getEnemyChampion1();
		enemy3 = champions.getEnemyChampion1();
		enemy4 = champions.getEnemyChampion1();
		enemy5 = champions.getEnemyChampion1();
		 
		
		
	}
	
	/**
	 * Get method to get the GPM value as a String
	 * @return gpmtostring
	 */
	public String getGPMToString() {
		String gpmtostring = Double.toString(gpm);
		return gpmtostring;
	}
	
	/**
	 * Get method for the rank of the player
	 * @return  rank
	 */
	public String getRank() {
		return rank;
	}
	
	/**
	 * Get method for the players CS per minute value
	 * @return cspermin
	 */
	public double getCspermin() {
		return cspermin;
	}
	
	/**
	 * Get method for the wards a player has bought in a game
	 * @return wards
	 */
	public long getWards() {
		return wards;
	}
	
	/**
	 * Get method for the match result
	 * @return winlose
	 */
	public String getResult() {
		return winlose;
	}
	
	/**
	 * Get method for damage done by the player in a specific game
	 * @return ddtostring
	 */
	public String getDdToString() {
		String ddtostring = Double.toString(dmgdealt);
		return ddtostring;
	}
	
	/**
	 * Get method for players wards bought in a different format
	 * @return wardstostring
	 */
	public String getWardsToString() {
		String wardstostring = Long.toString(wards);
		return wardstostring;
	}
	
	/**
	 * Get method for players creep score in a String format
	 * @return cstostring
	 */
	public String getCStoString() {
		String cstostring = Long.toString(cs);
		return cstostring;
	}
	
	/**
	 * Get method for a players kills death assist ratio in a String format
	 * @return kdatostring
	 */
	public String getKDAToString() {
		String kdatostring = Double.toString(kda);
		return kdatostring;
		
	}
	
	/**
	 * Get method for the champion played.
	 * @return champion
	 */
	public String getChamp() {
		return champion;
	}
	
	/**
	 * Get method for teammate number 1 champion played
	 * @return champion1
	 */
	public String getChamp1() {
		return champion1;
	}
	
	/**
	 * Get method for teammate number 2 champion played
	 * @return champion2
	 */
	public String getChamp2() {
		return champion2;
	}
	
	/**
	 * Get method for teammate number 3 champion played
	 * @return champion3
	 */
	public String getChamp3() {
		return champion3;
	}
	
	/**
	 * Get method for teammate number 4 champion played
	 * @return champio4
	 */
	public String getChamp4() {
		return champion4;
	}
	
	/**
	 * Get method for enemy number 1 champion played
	 * @return enemy1
	 */
	public String getEnemy1() {
		return enemy1;
	}
	
	/**
	 * Get method for enemy number 2 champion played
	 * @return enemy2
	 */
	public String getEnemy2() {
		return enemy2;
	}
	
	/**
	 * Get method for enemy number 3 champion played
	 * @return enemy3
	 */
	public String getEnemy3() {
		return enemy3;
	}
	
	/**
	 * Get method for enemy number 4 champion played
	 * @return enemy4
	 */
	public String getEnemy4() {
		return enemy4;
	}
	
	/**
	 * Get method for enemy number 5 champion played
	 * @return enemy5
	 */
	public String getEnemy5() {
		return enemy5;
	}
	
	/**
	 * Get method for the kill death assist ratio evaluation as a String
	 * @return KDAevaluation
	 */
	public String getKDAevaluation() {
		
		if(kda < 1.5) {
		KDAevaluation = "Poor KDA";
		}
		else if(kda > 1.5 &&  kda<3) {
			KDAevaluation = "Average KDA";
		}
		else if(kda>3 && kda < 5) {
			KDAevaluation = "Good KDA";
		}
		else if(kda>5){
			KDAevaluation = "Insane KDA";
		}
		else {return "Something went wrong, unable to get a kda rating";}
		return KDAevaluation;
		
		}
	
	/**
	 * Get method for the ward evaluation of the player in a String format
	 * @return wardevaluation
	 */
	public String getWardEvaluation() {
		if(wards < 2) {
			wardevaluation = "poor";
		}
		else if(wards > 3 && wards < 10) {
			wardevaluation = "good";
	}
		else if(wards==3) {
			wardevaluation = "average";
		}
		else if(wards > 10) {
			wardevaluation = "Too many";
		}
	return wardevaluation;
}	
	/**
	 * Get method for evaluatin creep score in a String format
	 * @return csevaluation
	 */
	public String getCSevaluation() {
		if (cspermin < 6) {
			csevaluation = "poor";
		}
		else if(cspermin > 6 && cspermin < 8 ) {
			csevaluation = "good";
		}
		else if(cspermin > 8){
			csevaluation = "excellent";
		}
		return csevaluation;
	}
	
	
	/**
	 * Get method for evaluating the damage done by the player in a String format
	 * @return dmgevaluation
	 */
	public String getDMGevaluation() {
		if(dmgpermin < 600) {
			dmgevaluation = "poor";
		}
		else if (dmgpermin > 600 && dmgpermin < 800) {
			dmgevaluation = "average";
		}
		else if(dmgpermin > 800 && dmgpermin <1000) {
			dmgevaluation = "good";
		}
		else {
			dmgevaluation = "excellent";
		}
		return dmgevaluation;
	}
	
	/**
	 * Get method for getting feedback based on kda evaluation for the match
	 * @return kdafeedback
	 */
	public String getKDAfeedback() {
		if (KDAevaluation == "Poor KDA") {
			kdafeedback = "You need to play safer especially in the early stages of the game. A poor kda is usually the result of bad positioning in teamfights or lack of game knowledge while laning.";
		}
		else if(KDAevaluation == "Average KDA") {
			kdafeedback = "Your kda in this game is average which means you didn't nescessarily play poorly but you could improve by being more aggressive in the laning phase which results in minor leads that snowball to the later stages of the game.";
		}
		else if(KDAevaluation == "Good KDA") {
			kdafeedback = "You have managed a good kda evaluation in this game which means your mistakes were minor or you got fed early, keep playing the same way and focus on your macro to get even more fed.";
		}
		else if(KDAevaluation == "Insane KDA") {
			kdafeedback = "This game your kda is among smurf accounts, check your replay and look to replicate this performance in the future.";
		}
		return kdafeedback;
	}
	
	/**
	 * Get method for getting feedback based on ward evaluation in the game
	 * @return wardfeedback
	 */
	public String getWardfeedback() {
		if(wardevaluation == "poor") {
			wardfeedback = "You place too little vision wards, average players place atleast 2 vision wards per game. This also helps with your teams success.";
		}
		else if(wardevaluation == "average") {
			wardfeedback = "Your warding this game was average meaning you do as well as any other player in your rank, try placing a couple more wards for optimal vision.";
		}
		else if(wardevaluation == "good") {
			wardfeedback = "Your ward score is good, keep it up and see your winrate steadily climb.";
		}
		else if(wardevaluation == "Too many") {
			wardfeedback = "You purchased an insane amount of vision wards this game, excluding you playing a support this isn't an optimal way to spend your gold so aim to buy 4 to 9 vision wards per game.";
		}
		return wardfeedback;
	}
	
	/**
	 * Get method for getting feedback based on creepscore evaluation
	 * @return csfeedback
	 */
	public String getCSfeedback() {
		if(csevaluation == "poor") {
			csfeedback = "Your creepscore in this game is considered poor by average player standards, try going into custom games and work on your mechanics by first cs:ing without an opponent and once you get to 90cs/10 minutes add a bot to play againsts.";
		}
		else if(csevaluation == "good") {
			csfeedback = "You did a good job cs:ing this game but there is still room for improvement, your biggest improvement in this area comes from studying and knowing your matchup. Try to play more games and in those games focus on your positioning in lane to make perfecting your cs game easier.";
		}
		else if(csevaluation == "excellent") {
			csfeedback = "Excellent, your cs numbers are on par with challenger level players! Try to maintain this level in every game you play and you will see a steady climb in your rank.";
		}
		return csfeedback;
	}
	
	/**
	 * Get method for getting your score on the individual game based on all other analysis
	 * @return winstreakscore
	 */
	public String getWinstreakscore() {
		if(csevaluation == "poor" && wardevaluation == "poor") {
			winstreakscore = "0: This is the lowest score possible which indicates that you need to work on all aspects of your gameplay, work on your basics like cs:ing, positioning and warding more.";
		}
		else if(csevaluation == "good" && wardevaluation == "poor") {
			winstreakscore = "2: Your cs:ing is on an average level but you need to work on your vision game, use 75 gold everytime you back to base and buy some wards";
		}
		else if (csevaluation == "excellent" && wardevaluation == "poor") {
			winstreakscore = "4: Your farming game was on point but your vision needs working on, train in custom games and see your rank rise.";
		}
		else if (csevaluation == "poor" && wardevaluation == "average") {
			winstreakscore = "2: Your farming was really bad but your vision game was on par with an average player, go to custom games and work on your cs:ing.";
		}
		else if (csevaluation == "poor" && wardevaluation == "good") {
			winstreakscore = "3: Your farming this game was decent but your warding and positioning need to be worked on, your wardscore is too low to merit a better score.";
		}
		else if (csevaluation == "poor" && wardevaluation == "Too many") {
			winstreakscore = "2: You bought way too many wards this game and cs:ed badly, you must be playing a support.";
		}
		else if (csevaluation == "good" && wardevaluation == "average") {
			winstreakscore = "6: You were on par with average players this game which means you need to work on small things, we suggest improving your cs:ing by learning matchups and perfecting your mechanics in normal games.";
		}
		else if (csevaluation == "good" && wardevaluation == "good") {
			winstreakscore = "8: Better than average, try getting a ward or two more in your next game to achieve a higher ranking on ourn scale.";
		}
		
		else if (csevaluation == "good" && wardevaluation == "Too many") {
			winstreakscore = "7: Your cs:ing was on point but you use way too much money on wards, aim for 4 to 10 wards per game based on gamelength to optimize your vision game.";
		}
		else if (csevaluation == "excellent" && wardevaluation == "average") {
			winstreakscore = "9: Your cs:ing was on par with challenger players. Good job! Now just place a few more vision wards in your next games to perfect your vision game.";
		}
		else if (csevaluation == "excellent" && wardevaluation == "good") {
			winstreakscore = "10: Excellent job, play like this every game and you will rank up!";
		}
		else if (csevaluation == "excellent" && wardevaluation == "Too many") {
			winstreakscore = "8: Your cs:ing was excellent but you bought too many wards. This many wards should only be bought in 50+ minute games.";
		}
		return winstreakscore;
}
}

