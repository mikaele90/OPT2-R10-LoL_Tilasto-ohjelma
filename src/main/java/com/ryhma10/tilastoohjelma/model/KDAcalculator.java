package com.ryhma10.tilastoohjelma.model;



public class KDAcalculator{
	
	Gamedata g;
	double a = g.getAssist();
	double k = g.getKills();
	double d = g.getDeaths();
	
	public KDAcalculator(Gamedata g) {
		this.g = g;
	}
	
	public double getKDA() {
		
		double kda;
		
		kda = ((k+a)/d);
		return kda;
				}
	
				
	public void printKDA() {
		System.out.printf("Your KDA is: %.2f", + getKDA());
	}
	}




