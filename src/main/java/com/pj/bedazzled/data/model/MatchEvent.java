package com.pj.bedazzled.data.model;

import static com.pj.bedazzled.data.model.MatchEvent.MatchEventType.*;

public class MatchEvent {
    private final MatchEventType type;
    private final String scorer;

    public MatchEvent(MatchEventType type) {
        this(type, null);
    }

    public MatchEvent(MatchEventType type, String scorer) {
        this.type = type;
        this.scorer = scorer;
    }

    public enum MatchEventType {
        GOAL, HT, CONCEDED;
    }

    public MatchEventType getType() {
        return type;
    }

    public String getScorer() {
        return scorer;
    }

    public static MatchEvent getConceded() {
        return new MatchEvent(CONCEDED);
    }

    public static MatchEvent getHalfTime() {
        return new MatchEvent(HT);
    }

    public static MatchEvent getGoal(String scorer) {
        return new MatchEvent(MatchEventType.GOAL, scorer);
    }
}
