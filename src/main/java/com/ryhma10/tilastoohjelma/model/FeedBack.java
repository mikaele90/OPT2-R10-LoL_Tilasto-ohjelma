package com.ryhma10.tilastoohjelma.model;

public class FeedBack {
	
	//Esitellään tarvittavat muuttujat.
	int kills, assists, deaths, dmgdealt, cs;
	double killparticipation, kda, gamelength, cspermin, wards;
	String rank;
	String KDAevaluation, DMGevaluation,wardevaluation,resultprint;
	boolean winloss, result;
	
	//Iron statistiikat viime kuukauden keskiarvoilla
	double ironkda = 1.64;
	int ironcs = 81; 
	int irongold = 9485;
	double ironwardspergame = 0.55;
	
	//Bronze statistiikat viime kuukauden keskiarvoilla
	double bronzekda = 1.64;
	int bronzecs = 81; 
	int bronzegold = 9485;
	double bronzewardspergame = 0.55;
	
	
	
	//Konstruktorissa annetaan matsin tiedot ja lasketaan kda.
	public FeedBack (boolean winloss, String r, int k, int a, int d, int dmgd, int cs, double kp, double g, int w) {
		
		this.winloss = winloss;
		this.rank = r;
		this.kills = k;
		this.assists = a;
		this.deaths = d;
		this.dmgdealt = dmgd;
		this.cs = cs;
		this.wards = w;
		this.killparticipation = kp;
		this.gamelength = g;
		this.getKDA(k, a, d);
		this.getResult(winloss);
		this.getCsPerMin(cs, gamelength);
		this.getWards(w);
	}
	
	//Metodi kda:n laskemiseksi käytetään feedbackin konstruktorissa.
	public double getKDA(int kills, int assists,int deaths) {
		this.kills = kills;
		this.assists = assists;
		this.deaths = deaths;
		kda = (kills+assists)/deaths;
		return kda;
	}
	
	//metodi creepscore per minuutin selvittämiseksi
	public double getCsPerMin(int cs, double gamelength) {
		this.cs = cs;
		this.gamelength = gamelength;
		
		cspermin = cs/gamelength;
		return cspermin;
		
	}
	
	public int getWards(int w) {
		this.wards = w;
		return w;
	}
	
	
	public boolean getResult(boolean winloss) {
		
		if(winloss == true) {
			result = true;
		}
		else{
			result = false;
		}
		
		return result;
	}
	
	public void printResult() {
		if(result == true) {
			System.out.println("win");
		}
		else if (result == false) {
			System.out.println("loss");
		}
	}
	
	//Metodi palautteen hankkimiseksi kda:n perusteella.
	public String getKDAfeedback(FeedBack fb) {
		
		if(kda<1) {
		KDAevaluation = "Poor KDA";
		}
		else if(kda>1 && kda<3) {
			KDAevaluation = "Average KDA";
		}
		else if(kda>3 && kda<5) {
			KDAevaluation = "Good KDA";
		}
		else {
			KDAevaluation = "Insane KDA";
		}
		return KDAevaluation;
		
		}
	
	public String getWardEvaluation(FeedBack fb) {
		if(wards < ironwardspergame && rank == "iron") {
			wardevaluation = "poor";
		}
		else if(wards > ironwardspergame && rank == "iron") {
			wardevaluation = "good";
	}
		
	return wardevaluation;
}
	
	
	
		
	
	public static void main(String[] args) {
		FeedBack fb = new FeedBack(false, "iron",3,1,5,4,5,6,3,5);
		System.out.println(fb.getKDAfeedback(fb));
		System.out.println(fb.getWardEvaluation(fb));
		fb.printResult();
		
		
		
	}
}
