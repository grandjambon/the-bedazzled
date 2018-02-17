package com.pj.bedazzled.data.model;

/**
 * Wrapper around a match for a player, and includes 'goals scored' total
 */
public class PlayerMatch {

    private final Match match;
    private final int goalsScored;

    public PlayerMatch(Match match, int goalsScored) {
        this.match = match;
        this.goalsScored = goalsScored;
    }

    public Match getMatch() {
        return match;
    }

    public int getGoalsScored() {
        return goalsScored;
    }
}
