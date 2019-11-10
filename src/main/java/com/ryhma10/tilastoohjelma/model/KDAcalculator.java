package com.ryhma10.tilastoohjelma.model;



public class KDAcalculator{
	
	Gamedata g = new Gamedata();
	double a = g.getAssits();
	double k = g.getKills();
	double d = g.getDeaths();
	
	public KDAcalculator() {
		
		
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




