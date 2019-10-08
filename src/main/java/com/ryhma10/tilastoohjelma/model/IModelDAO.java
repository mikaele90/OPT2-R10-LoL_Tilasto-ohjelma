package com.ryhma10.tilastoohjelma.model;

import java.util.List;

public interface IModelDAO{
	public abstract Profile  readProfile(String name);
	public abstract boolean createGamedata(Gamedata gamedata);
	public abstract List<Gamedata> readGames();
	public abstract List<Gamedata> readSpesificGames(String pname);
	public abstract double gpmCalculus(double time, double gold);
	public abstract boolean deleteGame(int gameid);


}
