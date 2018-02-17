package com.pj.bedazzled.data.model.ui;

import java.util.LinkedList;
import java.util.List;

public class UiMatch {

    private final String opponent;
    private final int number;
    private final int season;

    private String header;
    private String halfTimeScore;
    private String finalScore;

    private final List<UiMatchEvent> firstHalf = new LinkedList<>();
    private final List<UiMatchEvent> secondHalf = new LinkedList<>();

    private List<UiMatchEvent> thisHalf = firstHalf;

    public UiMatch(String opponent, int number, int season) {
        header = "BeDazzled v " + opponent;
        this.opponent = opponent;
        this.number = number;
        this.season = season;
    }

    /**
     *
     * @param score string representation of the score
     * @param scorer if conceded, leave blank
     */
    public void addGoal(String score, String scorer) {
        thisHalf.add(new UiMatchEvent(score, scorer));
        finalScore = "FT: BeDazzled " + score + " " + opponent;
    }

    public void addHalfTime(String score) {
        halfTimeScore = "HT: BeDazzled " + score + " " + opponent;
        thisHalf = secondHalf;
    }

    public String getHeader() {
        return header;
    }

    public String getHalfTimeScore() {
        return halfTimeScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public List<UiMatchEvent> getFirstHalf() {
        return firstHalf;
    }

    public List<UiMatchEvent> getSecondHalf() {
        return secondHalf;
    }

    public int getNumber() {
        return number;
    }

    public int getSeason() {
        return season;
    }
}

