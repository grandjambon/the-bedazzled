package com.pj.bedazzled.data.model;

/**
 * Represent the season
 */
@SuppressWarnings("unused")
public class Season {

    private final Grade grade;
    private final int number;
    private final int numGames;
    private final int wins;
    private final int draws;
    private final int losses;
    private final int goalsFor;
    private final int goalsAgainst;
    private final String honours;

    public Season(Grade grade, int number, int numGames, int wins, int draws, int losses, int goalsFor, int goalsAgainst, String honours) {
        this.grade = grade;
        this.number = number;
        this.numGames = numGames;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.honours = honours;
    }

    public int getNumGames() {
        return numGames;
    }

    public int getNumber() {
        return number;
    }

    public Grade getGrade() {
        return grade;
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

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public String getHonours() {
        return honours;
    }

    /**
     * Seasons 1-8 were of size 14
     * Season 9 was size 11
     * Seasons 10-40 were of size 10
     * Season 41 onwards are of size 14
     * @param seasonNumber
     */
    public static int getFullSeasonSize(int seasonNumber) {
        if (seasonNumber == 9) {
            return 11;
        } else if (seasonNumber >= 1 && seasonNumber <= 8) {
            return 14;
        } else if (seasonNumber >= 10 && seasonNumber <=40) {
            return 10;
        } else { // 41 and up, currently
            return 14;
        }

    }

    public static int getSeasonCost(int seasonNumber) {
        if (seasonNumber >=31 && seasonNumber <=39) {
            return 475;
        } else if (seasonNumber == 40) {
            return 500;
        } else { // 41 and up, currently
            return 700;
        }

    }
}
