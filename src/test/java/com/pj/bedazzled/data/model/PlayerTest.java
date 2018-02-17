package com.pj.bedazzled.data.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
* Test for PPG
*/
public class PlayerTest {

    @Test
    public void testPPG() {
        Player player = new Player("test");

        player.incrementWins();
        player.incrementWins();
        player.incrementWins();
        player.incrementWins();
        player.incrementWins();
        player.incrementWins();
        player.incrementWins();

        player.incrementDraws();
        player.incrementDraws();

        player.incrementLosses();
        player.incrementLosses();
        player.incrementLosses();
        player.incrementLosses();

        System.out.println(player.getPpg());
    }

}