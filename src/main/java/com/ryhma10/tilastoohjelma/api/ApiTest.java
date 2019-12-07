package com.ryhma10.tilastoohjelma.api;

import com.ryhma10.tilastoohjelma.model.FromApiToDatabase;

public class ApiTest {
	
	public static void main(String[] args) {
		
		FromApiToDatabase shieeet = new FromApiToDatabase();
		shieeet.getDataFromEUW();
	}
}
