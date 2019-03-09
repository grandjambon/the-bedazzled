package com.pj.bedazzled.data;

import com.pj.bedazzled.data.model.Costs;
import com.pj.bedazzled.data.model.Match;
import com.pj.bedazzled.data.model.Player;
import com.pj.bedazzled.data.model.TotalDebt;
import com.pj.bedazzled.springboot.controller.BeDazzledConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BeDazzledConfiguration.class})
public class BeDazzledDataManagerTest {

    @Autowired
    private BeDazzledDataManager dataManager;

    @Test
    public void testGetAppearances() throws IOException {
        Collection<Player> players = dataManager.getPlayers();
        assertNotNull(players);
        assertFalse(players.isEmpty());
    }

    @Test
    public void testGetCosts() throws IOException {
        Map<String, Costs.Debt> costs = dataManager.getCosts(34);
        costs.forEach((player, debt) -> System.out.format("%s = %s\n", player, debt.getTotal()));

        // verify that they add up to a diff of less than a pound out
        assertEquals(475, costs.values().stream().mapToDouble(Costs.Debt::getTotal).sum(), 1);
    }

    @Test
    public void testGetAccounts() throws IOException {
        Map<String, TotalDebt> debts = dataManager.getAccounts();
        debts.forEach((player, debt) -> System.out.format("%-12s : %10s\n", player, debt.getDebtAsEndOfLastSeason()));

        assertNotNull(debts);
    }

    @Test
    public void testGetMatches() throws IOException {
        Map<String, Match> matches = dataManager.getMatchesForSeason("season34");
        assertThat(matches.size(), is(10));
        assertThat(matches.get("match-09-report.txt").isForfeit(), is(true));
    }

}