package com.pj.bedazzled.data.model;

import java.util.List;

import static com.pj.bedazzled.data.model.Match.Outcome.DRAW;
import static com.pj.bedazzled.data.model.Match.Outcome.LOSS;
import static com.pj.bedazzled.data.model.Match.Outcome.WIN;

public class Match {

    public enum Outcome {
        WIN("DDEDD3"), LOSS("F5C1C1"), DRAW("F5F7CD");

        final String colour;

        Outcome(String colour) {
            this.colour = colour;
        }

        public String getColour() {
            return colour;
        }
    }

    private final int seasonNumber;
    private final int number;
    private final String opponent;
    private final String goalie;
    private final List<String> players;
    private final List<MatchEvent> events;
    private final int goalsFor;
    private final int goalsAgainst;
    private final Grade grade;

    public Match(int seasonNumber, int number, String opponent, String goalie, List<String> players, List<MatchEvent> events) {
        this.seasonNumber = seasonNumber;
        this.number = number;
        this.opponent = opponent;
        this.goalie = goalie;
        this.players = players;
        this.events = events;

        int gf = 0;
        int ga = 0;

        for (MatchEvent event : events) {
            switch (event.getType()) {
                case CONCEDED:
                    ga++;
                    break;
                case GOAL:
                    gf++;
                    break;
            }
        }
        this.goalsFor = gf;
        this.goalsAgainst = ga;
        this.grade = Grade.getGrade(seasonNumber);
    }

    public String getOpponent() {
        return opponent;
    }

    public String getGoalie() {
        return goalie;
    }

    public List<String> getPlayers() {
        return players;
    }

    public List<MatchEvent> getMatchEvents() {
        return events;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public Outcome getOutcome() {
        if (goalsAgainst > goalsFor) {
            return LOSS;
        } else if (goalsAgainst < goalsFor) {
            return WIN;
        }
        return DRAW;
    }

    public int getNumber() {
        return number;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public Grade getGrade() {
        return grade;
    }
}
