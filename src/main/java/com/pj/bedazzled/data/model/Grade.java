package com.pj.bedazzled.data.model;

/**
 * This represents the Division we were in.
 *
 * TRANSITION = the season when they shifted from 16 to 12 teams.
 */
public enum Grade {
    A_GRADE("e6fffa"), B_GRADE("ffffe6"), TRANSITION("ffe6e6");

    private String color;

    Grade(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static Grade getGrade(int season) {
        if (season == 9)
            return TRANSITION;
        else if (season < 15 || season == 16 || season == 17) {
            return B_GRADE;
        }

        // effectively
        return A_GRADE;
    }

    /**
     *
     * @param seasonName of form "season03"
     * @return the grade
     */
    public static Grade getGrade(String seasonName) {
        int seasonNumber = Integer.valueOf(seasonName.replace("season", ""));
        return getGrade(seasonNumber);
    }

}
