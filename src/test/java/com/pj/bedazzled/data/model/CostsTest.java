package com.pj.bedazzled.data.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CostsTest {

    @Test
    void testCosts() {
        Costs costs = new Costs(475, 10);
        assertThat(costs.getCostPerGame()).isCloseTo(47.5, within(0.001));
        assertThat(costs.getNumGamesPerSeason()).isEqualTo(10);
        assertThat(costs.getTotalSeasonCost()).isCloseTo(475.0, within(0.001));
    }

    @Test
    void testAddDebt() {
        Costs costs = new Costs(475, 10);
        costs.setNumPlayers(1, 6);
        costs.setNumPlayers(2, 5);

        costs.addDebt("Phil", 1);
        costs.addDebt("Baz", 2);
        costs.addDebt("Phil", 2);

        assertThat(costs.getPlayerToDebt().get("Phil").getTotal()).isEqualTo(17.41);
        assertThat(costs.getPlayerToDebt().get("Baz").getTotal()).isEqualTo(9.5);
    }

    @Test
    void testCostsPerGame() {
        Costs costs = new Costs(100, 4);
        costs.setNumPlayers(1, 5);
        costs.setNumPlayers(2, 4);
        costs.setNumPlayers(3, 2);
        costs.setNumPlayers(4, 5);

        assertThat(costs.getMatchCosts(1).getCostPerPlayer().doubleValue()).isEqualTo(5.0);
        assertThat(costs.getMatchCosts(2).getCostPerPlayer().doubleValue()).isEqualTo(6.25);
        assertThat(costs.getMatchCosts(3).getCostPerPlayer().doubleValue()).isEqualTo(12.5);
        assertThat(costs.getMatchCosts(4).getCostPerPlayer().doubleValue()).isEqualTo(5.0);
    }
}
