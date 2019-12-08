package com.ryhma10.tilastoohjelma.model;

public class GamedataWrapper {

    private int gameId;
    private long riotId;
    private String date;
    private String summoner;
    private String champion;
    private String result;
    private int profileId;

    public GamedataWrapper(int gameId, long riotId, String date, String summoner, String champion, String result, int profileId) {
        this.gameId = gameId;
        this.riotId = riotId;
        this.date = date;
        this.summoner = summoner;
        this.champion = champion;
        this.result = result;
        this.profileId = profileId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public long getRiotId() {
        return riotId;
    }

    public void setRiotId(long riotId) {
        this.riotId = riotId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummoner() {
        return summoner;
    }

    public void setSummoner(String summoner) {
        this.summoner = summoner;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

}
