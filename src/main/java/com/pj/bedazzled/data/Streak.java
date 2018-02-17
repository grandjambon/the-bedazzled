package com.pj.bedazzled.data;

/**
 * Created by phil on 05/08/17.
 */
public class Streak {

    public enum StreakType {apps, goalsInApps, goalsInGames};

    private final String name;
    private final String start;
    private final String end;

    private final int total;

    private final StreakType type;

    public Streak(String name, String start, String end, int total, StreakType type) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.total = total;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getTotal() {
        return total;
    }

    public StreakType getType() {
        return type;
    }
}
