package com.pj.bedazzled.data.model;

public class CountingStat {

    private final String name;
    private int countingStat;

    public CountingStat(String name) {
        this.name = name;
    }

    public void increment() {
        countingStat++;
    }

    public String getName() {
        return name;
    }

    public int getCountingStat() {
        return countingStat;
    }
}
