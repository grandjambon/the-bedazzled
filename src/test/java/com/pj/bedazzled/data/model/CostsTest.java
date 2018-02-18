package com.pj.bedazzled.data.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CostsTest {

    @Test
    public void testCosts() {
        Costs costs = new Costs(475, 10);
        assertEquals(47.5, costs.getCostPerGame(), 0.001);
        assertEquals(10, costs.getNumGamesPerSeason());
        assertEquals(475, costs.getTotalSeasonCost(), 0.001);
    }

    @Test
    public void testAddDebt() {
        Costs costs = new Costs(475, 10);
        costs.setNumPlayers(1, 6);
        costs.setNumPlayers(2, 5);

        costs.addDebt("Phil", 1);
        costs.addDebt("Baz", 2);
        costs.addDebt("Phil", 2);

        assertEquals(17.41, costs.getPlayerToDebt().get("Phil").getTotal(), 0.00);
        assertEquals(9.5, costs.getPlayerToDebt().get("Baz").getTotal(), 0.00);
    }

    @Test
    public void testCostsPerGame() {
        Costs costs = new Costs(100, 4);
        costs.setNumPlayers(1, 5);
        costs.setNumPlayers(2, 4);
        costs.setNumPlayers(3, 2);
        costs.setNumPlayers(4, 5);

        assertEquals(5.0, costs.getMatchCosts(1).getCostPerPlayer().doubleValue(), 0.0);
        assertEquals(6.25, costs.getMatchCosts(2).getCostPerPlayer().doubleValue(), 0.0);
        assertEquals(12.5, costs.getMatchCosts(3).getCostPerPlayer().doubleValue(), 0.0);
        assertEquals(5.0, costs.getMatchCosts(4).getCostPerPlayer().doubleValue(), 0.0);
    }
}