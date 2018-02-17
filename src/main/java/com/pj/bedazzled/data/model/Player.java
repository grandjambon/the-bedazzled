package com.pj.bedazzled.data.model;

import java.math.BigDecimal;

/**
 * PoJo to hold onto Player data.
 *
 * Maybe extend to include "goals"
 */
@SuppressWarnings("unused")
public class Player {

    private final String name;

    private int apps = 0;
    private int goalieApps = 0;
    private int goals = 0;
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;

    public Player(String player) {
        this.name = player;
    }

    public String getName() {
        return name;
    }

    public int getApps() {
        return apps;
    }

    public int getGoalieApps() {
        return goalieApps;
    }

    public int getGoals() {
        return goals;
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

    public String getPpg() {
        BigDecimal points = new BigDecimal(3*wins + draws);
        BigDecimal games = new BigDecimal(wins+draws+losses);
        BigDecimal ppg = points.divide(games, 3, BigDecimal.ROUND_DOWN);

        return String.format("%.3f", ppg.doubleValue());
    }

    // ------------- increment ------------

    public void incrementApps() {
        apps++;
    }

    public void incrementGoalieApps() {
        goalieApps++;
    }

    public void incrementGoals() {
        goals++;
    }

    public void incrementWins() { wins++; }
    public void incrementDraws() { draws++; }
    public void incrementLosses() { losses++; }
}
