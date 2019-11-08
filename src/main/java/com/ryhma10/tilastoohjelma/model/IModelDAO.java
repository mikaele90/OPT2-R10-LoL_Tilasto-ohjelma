package com.ryhma10.tilastoohjelma.model;

import java.util.List;


public interface IModelDAO{
	public abstract Profile readProfile(String name);
	public abstract boolean createGamedata(String name, Gamedata gamedata, Item item);
	//public abstract boolean removeGame(int gameid);
	public abstract List<Gamedata> readGames();
	public abstract List<Item> readItems();
	public abstract List<Gamedata> readSpecificGame(String name);
	public List<Item> readGamesWithItems();
	public List<Object[]> readGamesTestaus();


}
  