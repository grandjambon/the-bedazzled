package com.pj.bedazzled.data.model;

public class OpponentRecord extends Record {

    private final String opponent;

    public OpponentRecord(String opponent, int played, int wins, int draws, int losses, int goalsFor, int goalsAgainst) {
        super(played, wins, draws, losses, goalsFor, goalsAgainst);
        this.opponent = opponent;
    }

    public String getOpponent() {
        return opponent;
    }
}
