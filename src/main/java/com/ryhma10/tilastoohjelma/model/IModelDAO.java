package com.ryhma10.tilastoohjelma.model;

import java.util.List;


public interface IModelDAO{

	public abstract SoftwareProfile readProfile(String name);
	public abstract boolean createGamedata(String name, Gamedata gamedata, Item item, Team team, Additional additional);
	public abstract List<Gamedata> readGames();
	public abstract double gpmCalculus(double time, double gold);
	public abstract boolean deleteGame(int gameid);
	public abstract boolean addProfile(SoftwareProfile profile);
	public abstract String createProfile(String profileName, String profilePassword, String defaultRegion, String defaultLanguage, String defaultRiotAccount, String riotAPIKey);
	public abstract String loginProfile(String profileName, String profilePassword);
	public abstract SoftwareProfile setLoggedInProfile(String profileName, String profilePassword);
	public abstract boolean updateProfile(SoftwareProfile currentProfile);
	public abstract List<Item> readItems();
	public abstract List<Gamedata> readSpecificProfilesGames(String name);
	public abstract List<Item> readGamesWithItems();
	public abstract List<Additional> readAdditionalData(long riotid);
	public abstract List<Team> readTeamComposition(long riotid);
	public abstract Gamedata readOneGame(long riotid);

}
  