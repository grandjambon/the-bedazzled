package com.pj.bedazzled.data.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void testPPG() {
        Player player = new Player("test");

        for (int i = 0; i < 7; i++) player.incrementWins();
        for (int i = 0; i < 2; i++) player.incrementDraws();
        for (int i = 0; i < 4; i++) player.incrementLosses();

        // 7 wins * 3 + 2 draws * 1 = 23 points / 13 games = 1.769
        assertThat(player.getPpg()).isEqualTo("1.769");
    }
}
