package com.ryhma10.tilastoohjelma.model;


/**
 * Unused KDAcalculator class from OTP1
 * @author Janari
 *
 */
public class KDAcalculator{
	
	Gamedata g;
	double a = g.getAssist();
	double k = g.getKills();
	double d = g.getDeaths();
	
	/**
	 * Constructor to KDAcalculator
	 * @param Gamedata to be used
	 */
	public KDAcalculator(Gamedata g) {
		this.g = g;
	}
	
	/**
	 * Get method for KDA
	 * @return kda
	 */
	public double getKDA() {
		
		double kda;
		
		kda = ((k+a)/d);
		return kda;
				}
	
	/**
	 * Print method for testing KDAcalculator
	 */
	public void printKDA() {
		System.out.printf("Your KDA is: %.2f", + getKDA());
	}
	}




