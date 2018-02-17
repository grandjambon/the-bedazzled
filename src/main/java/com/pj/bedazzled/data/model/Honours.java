package com.pj.bedazzled.data.model;

public class Honours {
    public static String getHonours(int season) {
        switch (season) {
            case 19:
            case 20:
            case 22:
            case 26:
            case 28:
            case 29:
            case 30:
                return "Champions";
            case 18:
            case 21:
            case 23:
            case 25:
                return "RU";
            case 14:
                return "Promoted (2nd)";
            case 17:
                return "Promoted (1st)";
            default:
                return "";
        }
    }
}
