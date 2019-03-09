package com.pj.bedazzled.data.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Costs {
    private final BigDecimal totalSeasonCost;
    private final int numGamesPerSeason;
    private final BigDecimal costPerGame;

    private final Map<String, Debt> playerToDebt = new TreeMap<>();
    private final MatchCosts[] matchCosts;

    public Costs(long costPerSeason, int numGamesPerSeason) {
        this.totalSeasonCost = new BigDecimal(costPerSeason);
        this.numGamesPerSeason = numGamesPerSeason;
        this.costPerGame = totalSeasonCost.divide(new BigDecimal(numGamesPerSeason), 2, BigDecimal.ROUND_FLOOR);
        this.matchCosts = new MatchCosts[numGamesPerSeason];
    }

    public void addDebt(String player, int matchNumber) {
        MatchCosts matchCost = getMatchCosts(matchNumber);
        playerToDebt.computeIfAbsent(player, k -> new Debt()).add(matchCost.getCostPerPlayer());
    }

    public void addForfeitDebt(long numForfeits) {
        playerToDebt.forEach((s, debt) -> debt.handleForfeit(numForfeits));
    }

    public void setNumPlayers(int matchNumber, int numPlayers) {
        matchCosts[matchNumber-1] = new MatchCosts(matchNumber, costPerGame, numPlayers);
    }

    public double getTotalSeasonCost() {
        return totalSeasonCost.doubleValue();
    }

    public int getNumGamesPerSeason() {
        return numGamesPerSeason;
    }

    public double getCostPerGame() {
        return costPerGame.doubleValue();
    }

    public Map<String, Debt> getPlayerToDebt() {
        return playerToDebt;
    }

    public MatchCosts getMatchCosts(int matchNumber) {
        MatchCosts matchCost = matchCosts[matchNumber - 1];
        if (matchCost == null) {
            throw new RuntimeException("matchNumber {} has not been initialised");
        }
        return matchCost;
    }

    public static class Debt {
        private BigDecimal d = new BigDecimal("0");

        private void add(BigDecimal amount) {
            d = d.add(amount);
        }

        public double getTotal() {
            return d.doubleValue();
        }

        public BigDecimal getTotalAsBigDecimal() {
            return d;
        }

        private void handleForfeit(long numForfeits) {
            d = d.multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(10-numForfeits), BigDecimal.ROUND_UP);
        }
    }

    public static class MatchCosts {
        private int matchNumber;
        private BigDecimal totalCost;
        private int numPlayers;
        private BigDecimal costPerPlayer;

        public MatchCosts(int matchNumber, BigDecimal totalCost, int numPlayers) {
            this.matchNumber = matchNumber;
            this.totalCost = totalCost;
            this.numPlayers = numPlayers;
            this.costPerPlayer = (numPlayers == 0) ? BigDecimal.ZERO :totalCost.divide(new BigDecimal(numPlayers), 2, BigDecimal.ROUND_FLOOR);
        }

        public BigDecimal getTotalCost() {
            return totalCost;
        }

        public int getMatchNumber() {
            return matchNumber;
        }

        public BigDecimal getCostPerPlayer() {
            return costPerPlayer;
        }

        public int getNumPlayers() {
            return numPlayers;
        }
    }

}
