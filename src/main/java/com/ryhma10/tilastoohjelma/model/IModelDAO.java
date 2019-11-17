package com.ryhma10.tilastoohjelma.model;

import java.util.List;


public interface IModelDAO{
	public abstract SoftwareProfile readProfile(String name);
	public abstract boolean createGamedata(Gamedata gamedata);
	public abstract List<Gamedata> readGames();
	public abstract List<Gamedata> readSpesificGames(String pname);
	public abstract double gpmCalculus(double time, double gold);
	public abstract boolean deleteGame(int gameid);
	public abstract boolean addProfile(SoftwareProfile profile);
	public abstract String createProfile(String profileName, String profilePassword, String defaultRegion, String defaultLanguage, String defaultRiotAccount, String riotAPIKey);
	public abstract String loginProfile(String profileName, String profilePassword);
	public abstract SoftwareProfile setLoggedInProfile(String profileName, String profilePassword);
}
  