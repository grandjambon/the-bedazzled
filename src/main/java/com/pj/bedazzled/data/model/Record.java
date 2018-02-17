package com.pj.bedazzled.data.model;

public class Record {
    private final int played;
    private final int wins;
    private final int draws;
    private final int losses;

    private final int goalsFor;
    private final int goalsAgainst;

    public Record(int played, int wins, int draws, int losses, int goalsFor, int goalsAgainst) {
        this.played = played;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }


    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getPlayed() {
        return played;
    }
}
