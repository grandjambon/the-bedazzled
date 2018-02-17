package com.pj.bedazzled.data.model.ui;

public class UiMatchEvent {

    private final String score;
    private final String scorer;

    public UiMatchEvent(String score, String scorer) {
        this.score = score;
        this.scorer = scorer;
    }

    public String getScore() {
        return score;
    }

    public String getScorer() {
        return scorer;
    }
}
