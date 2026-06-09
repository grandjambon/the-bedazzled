package com.pj.bedazzled.data;

import com.pj.bedazzled.data.model.Costs;
import com.pj.bedazzled.data.model.Match;
import com.pj.bedazzled.data.model.Player;
import com.pj.bedazzled.data.model.TotalDebt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest
class BeDazzledDataManagerTest {

    @Autowired
    private BeDazzledDataManager dataManager;

    @Test
    void testGetAppearances() throws IOException {
        Collection<Player> players = dataManager.getPlayers();
        assertThat(players).isNotEmpty();
    }

    @Test
    void testGetCosts() throws IOException {
        Map<String, Costs.Debt> costs = dataManager.getCosts(34);
        double total = costs.values().stream().mapToDouble(Costs.Debt::getTotal).sum();
        assertThat(total).isCloseTo(475, within(1.0));
    }

    @Test
    void testGetAccounts() throws IOException {
        Map<String, TotalDebt> debts = dataManager.getAccounts();
        assertThat(debts).isNotEmpty();
    }

    @Test
    void testGetMatches() {
        Map<String, Match> matches = dataManager.getMatchesForSeason("season34");
        assertThat(matches).hasSize(10);
        assertThat(matches.get("match-09-report.txt").isForfeit()).isTrue();
    }
}
