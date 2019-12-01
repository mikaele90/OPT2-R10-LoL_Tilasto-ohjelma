package com.ryhma10.tilastoohjelma.model;

public class FeedBack {
	
	//Esitellään tarvittavat muuttujat.
	long kills, assists, deaths, cs, gold, wards, riotid;
	double kda, gamelength, cspermin, dmgdealt, dmgpermin, gpm;
	String rank, champion, winlose, result, KDAevaluation, DMGevaluation, wardevaluation, csevaluation,dmgevaluation, champion1, champion2, champion3, champion4, enemy1, enemy2, enemy3,enemy4,enemy5;
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
		
	
	
	
	//Konstruktorissa annetaan matsin tiedot ja lasketaan kda.
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
	public String getGPMToString() {
		String gpmtostring = Double.toString(gpm);
		return gpmtostring;
	}
	
	public String getRank() {
		return rank;
	}
	
	public double getCspermin() {
		return cspermin;
	}
	
	public long getWards() {
		return wards;
	}
				
	public String getResult() {
		return winlose;
	}
	
	public String getDdToString() {
		String ddtostring = Double.toString(dmgdealt);
		return ddtostring;
	}
	
	public String getWardsToString() {
		String wardstostring = Long.toString(wards);
		return wardstostring;
	}
	
	public String getCStoString() {
		String cstostring = Long.toString(cs);
		return cstostring;
	}
	
	public String getKDAToString() {
		String kdatostring = Double.toString(kda);
		return kdatostring;
		
	}
	
	public String getChamp() {
		return champion;
	}
	
	public String getChamp1() {
		return champion1;
	}
	
	public String getChamp2() {
		return champion2;
	}
	
	public String getChamp3() {
		return champion3;
	}
	
	public String getChamp4() {
		return champion4;
	}
	
	public String getEnemy1() {
		return enemy1;
	}
	
	public String getEnemy2() {
		return enemy1;
	}
	
	public String getEnemy3() {
		return enemy1;
	}
	
	public String getEnemy4() {
		return enemy4;
	}
	
	public String getEnemy5() {
		return enemy5;
	}
	
	//Metodi palautteen hankkimiseksi kda:n perusteella.
	public String getKDAfeedback() {
		
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
	
	//Tehdään analyysi wardien käytöstä keskiarvojen perusteella.
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
	//Tehdään analyysi creepscoren perusteella.
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
}
