package com.pj.bedazzled.data;

import com.pj.bedazzled.data.filters.TrivialMatchFilter;
import com.pj.bedazzled.data.model.*;
import com.pj.bedazzled.data.util.FileUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pj.bedazzled.data.model.MatchEvent.MatchEventType.GOAL;
import static com.pj.bedazzled.data.util.FileUtils.getSeasonForDirectory;
import static com.pj.bedazzled.data.util.FileUtils.getSeasonNames;

/**
 * Responsible for getting all the BeDazzled data together
 */
@SuppressWarnings("unused")
public class BeDazzledDataManager {

    public static final int CURRENT_SEASON = 33;

    private Map<String, Map<String, Match>> allTimeHistoryMap = new TreeMap<>();

    private Collection<String> dummyScorers = new TreeSet<>();

    @PostConstruct
    void initialise() throws IOException {
        Collection<String> seasons = getSeasonNames();
        for (String season : seasons) {
            Map<String, Match> matchesForSeason = getSeasonForDirectory(season);
            allTimeHistoryMap.put(season, matchesForSeason);
        }

        dummyScorers.add("OG");
        dummyScorers.add("FORFEIT");
    }

    public Collection<Player> getPlayers(MatchFilter filter) throws IOException {

        Map<String, Player> nameToPlayer = new TreeMap<>();

        for (Map.Entry<String, Map<String, Match>> season : allTimeHistoryMap.entrySet()) {

            // this is per season
            String seasonName = season.getKey();
            Grade grade = Grade.getGrade(seasonName);

            for (Map.Entry<String, Match> entry : season.getValue().entrySet()) {

                Match match = entry.getValue();
                if (filter.include(match)) {

                    // goalie apps
                    String goalie = match.getGoalie();
                    if (goalie != null) {
                        Player goalieApp = getPlayer(nameToPlayer, goalie);
                        goalieApp.incrementGoalieApps();
                        switch (match.getOutcome()) {
                            case WIN:
                                goalieApp.incrementWins();
                                break;
                            case LOSS:
                                goalieApp.incrementLosses();
                                break;
                            case DRAW:
                                goalieApp.incrementDraws();
                        }

                    }

                    // outfield apps
                    for (String player : match.getPlayers()) {
                        Player appearance = getPlayer(nameToPlayer, player);
                        appearance.incrementApps();
                        switch (match.getOutcome()) {
                            case WIN:
                                appearance.incrementWins();
                                break;
                            case LOSS:
                                appearance.incrementLosses();
                                break;
                            case DRAW:
                                appearance.incrementDraws();
                        }
                    }

                    // goals
                    match.getMatchEvents().stream().filter(event -> event.getType() == GOAL).forEach(event -> {
                        if (!dummyScorers.contains(event.getScorer())) {
                            getPlayer(nameToPlayer, event.getScorer()).incrementGoals();
                        }
                    });
                }
            }
        }

        return nameToPlayer.values();
    }

    public Collection<Player> getPlayers() throws IOException {
        return getPlayers(new TrivialMatchFilter());
    }

    public Map<String, Match> getMatchesForSeason(String seasonName) {
        return allTimeHistoryMap.get(seasonName);
    }

    public Collection<Match> getMatches(MatchFilter filter) {
        Collection<Match> matches = new ArrayList<>();
        for (Map.Entry<String, Map<String, Match>> season : allTimeHistoryMap.entrySet()) {
            // this is per season
//            String seasonName = season.getKey();
//            Grade grade = Grade.getGrade(seasonName);
            for (Map.Entry<String, Match> entry : season.getValue().entrySet()) {
                Match match = entry.getValue();
                if (filter.include(match)) {
                    matches.add(match);
                }
            }
        }
        return matches;
    }

    public Collection<CountingStat> getGoalscoringChart(String seasonName) {
        Map<String, CountingStat> goalsChart = new TreeMap<>();

        Map<String, Match> season = allTimeHistoryMap.get(seasonName);
        for (Match match : season.values()) {
            for (MatchEvent matchEvent : match.getMatchEvents()) {
                if (matchEvent.getType() == GOAL) {
                    String player = matchEvent.getScorer();
                    CountingStat countingStat = goalsChart.get(player);
                    if (countingStat == null) {
                        countingStat = new CountingStat(player);
                        goalsChart.put(player, countingStat);
                    }
                    countingStat.increment();
                }
            }
        }

        Collection<CountingStat> goals = new TreeSet<>(new CountingStatComparator());
        goals.addAll(goalsChart.values());
        return goals;
    }

    public Collection<CountingStat> getAppearancesChart(String seasonName) {
        Map<String, CountingStat> appearancesChart = new TreeMap<>();

        Map<String, Match> season = allTimeHistoryMap.get(seasonName);
        for (Match match : season.values()) {

            for (String player : match.getPlayers()) {
                CountingStat countingStat = appearancesChart.get(player);
                if (countingStat == null) {
                    countingStat = new CountingStat(player);
                    appearancesChart.put(player, countingStat);
                }
                countingStat.increment();
            }
        }

        Collection<CountingStat> apps = new TreeSet<>(new CountingStatComparator());
        apps.addAll(appearancesChart.values());
        return apps;
    }

    public Collection<Season> getSeasons() {
        Collection<Season> seasons = new LinkedList<>();

        for (Map.Entry<String, Map<String, Match>> season : allTimeHistoryMap.entrySet()) {

            String seasonName = season.getKey();
            int seasonNumber = Integer.valueOf(seasonName.replace("season", ""));

            int wins = 0;
            int losses = 0;
            int draws = 0;
            int goalsFor = 0;
            int goalsAgainst = 0;
            Grade grade = Grade.getGrade(seasonNumber);
            String honours = Honours.getHonours(seasonNumber);


            for (Map.Entry<String, Match> entry : season.getValue().entrySet()) {
                String matchName = entry.getKey();
                Match match = entry.getValue();

                switch (match.getOutcome()) {
                    case WIN:
                        wins++;
                        break;
                    case LOSS:
                        losses++;
                        break;
                    case DRAW:
                        draws++;
                }

                goalsFor+=match.getGoalsFor();
                goalsAgainst+=match.getGoalsAgainst();
            }
            seasons.add(new Season(grade, seasonNumber, season.getValue().size(), wins, draws, losses, goalsFor, goalsAgainst, honours));
        }
        return seasons;
    }

    public Collection<OpponentRecord> getOpponentRecords(Collection<String> opponentNames) {

        Collection<OpponentRecord> opponents = new TreeSet<>((o1, o2) -> {
            int compare = -1 * Integer.compare(o1.getPlayed(), o2.getPlayed());
            if (compare == 0 ) {
                compare = o1.getOpponent().compareTo(o2.getOpponent());
            }
            return compare;
        });
        for (String opp : opponentNames) {
            Collection<Match> matchesAgainstOpponent = getMatches(match -> match.getOpponent().equals(opp));

            int played = 0;
            int wins = 0;
            int draws = 0;
            int losses = 0;
            int goalsFor = 0;
            int goalsAgainst = 0;

            for (Match match : matchesAgainstOpponent) {
                played++;
                if (match.getGoalsFor() == match.getGoalsAgainst()) {
                    draws++;
                } else if (match.getGoalsFor() > match.getGoalsAgainst()) {
                    wins++;
                } else if (match.getGoalsFor() < match.getGoalsAgainst()) {
                    losses++;
                }

                goalsFor += match.getGoalsFor();
                goalsAgainst += match.getGoalsAgainst();
            }

            opponents.add(new OpponentRecord(opp, played, wins, draws, losses, goalsFor, goalsAgainst));
        }

        return opponents;
    }

    /**
     *
     * @return a Collection of names of all opponents we've faced
     */
    public Collection<String> getOpponents() {
        Set<String> opponents =
            getMatches((Match match) -> true).stream().map(Match::getOpponent).collect(Collectors.toSet());

        return opponents;
    }


    public Collection<Match> getMatchesAgainstOpponent(String opponent) {
        Collection<Match> matches = getMatches(match -> match.getOpponent().equals(opponent));
        return matches;
    }

    public Record getRecord(Collection<Match> matches) {
        int played = 0;
        int wins = 0;
        int draws = 0;
        int losses = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;

        for (Match match : matches) {
            played++;
            if (match.getGoalsFor() == match.getGoalsAgainst()) {
                draws++;
            } else if (match.getGoalsFor() > match.getGoalsAgainst()) {
                wins++;
            } else if (match.getGoalsFor() < match.getGoalsAgainst()) {
                losses++;
            }

            goalsFor += match.getGoalsFor();
            goalsAgainst += match.getGoalsAgainst();
        }
        return new Record(played, wins, draws, losses, goalsFor, goalsAgainst);
    }

    public Record getRecordForPlayer(Collection<PlayerMatch> playerMatches) {
        Collection<Match> matches = playerMatches.stream().map(PlayerMatch::getMatch).collect(Collectors.toList());
        return getRecord(matches);
    }

    public Collection<PlayerMatch> getPlayerMatches(String player) {
        Collection<Match> matches = getMatches(match -> match.getPlayers().contains(player) ||
                player.equals(match.getGoalie()));

        Collection<PlayerMatch> playerMatches = new ArrayList<>(matches.size());

        for (Match m : matches) {
            List<MatchEvent> goals = m.getMatchEvents().stream().filter(matchEvent -> matchEvent.getType() == GOAL &&
                    matchEvent.getScorer().equals(player)).collect(Collectors.toList());

            playerMatches.add(new PlayerMatch(m, goals.size()));
        }

        return playerMatches;
    }

    // ==================== Private Methods =================

    private Player getPlayer(Map<String, Player> playerToApp, String name) {
        Player app = playerToApp.get(name);
        if (app == null) {
            app = new Player(name);
            playerToApp.put(name, app);
        }
        return app;
    }

    // i is the minimum interesting streak
    public Collection<Streak> getAppStreaks(int minimumApp) throws IOException {
        Collection<Streak> streaks = new ArrayList<>();
        Collection<Match> matches = getMatches(match -> true).stream().sorted(new MatchComparator()).collect(Collectors.toList());

        for (Player player : getPlayers()) {
            int streak = 0;
            String streakStart = null;
            String streakEnd = null;

            for (Match match : matches) {
                if (match.getPlayers().contains(player.getName()) || (match.getGoalie() != null && match.getGoalie().equals(player.getName()))) {
                    streak++;
                    if (streakStart == null) {
                        streakStart = getMatchName(match);
                    }
                    streakEnd = getMatchName(match);
                } else {
                    if (streak >= minimumApp) {
                        streaks.add(new Streak(player.getName(), streakStart, streakEnd, streak, Streak.StreakType.apps));
                    }
                    streakStart = null;
                    streakEnd = null;
                    streak = 0;
                }
            }
            if (streak >= minimumApp) {
                streaks.add(new Streak(player.getName(), streakStart, streakEnd, streak, Streak.StreakType.apps));
            }
        }
        return streaks;
    }

    private String getMatchName(Match m) {
        return String.format("s%02dsm%02d", m.getSeasonNumber(), m.getNumber());
    }

    public Map<String, Streak> getGoalScoringStreaks(int i) {
        Collection<Match> matches = getMatches(match -> true).stream().sorted(new MatchComparator()).collect(Collectors.toList());



        return null;
    }

    public Map<String, Costs.Debt> getCosts(int season) {
        Collection<Match> matches = getMatches(match -> match.getSeasonNumber() == season);

        Costs costs = new Costs(475, matches.size());

        int i = 1;
        for (Match m : matches) {
            int matchNum = i;
            List<String> players = new ArrayList<>(m.getPlayers());
            if (m.getGoalie() != null) {
                players.add(m.getGoalie());
            }

            costs.setNumPlayers(matchNum, players.size());
            players.stream().forEach(player -> costs.addDebt(player, matchNum));
            i++;
        }
        return costs.getPlayerToDebt();
    }

    public Map<String, TotalDebt> getAccounts() throws IOException {
        Map<String, TotalDebt> debts = FileUtils.getDebts();
        Collection<String> currentSquad = FileUtils.currentSquadForAccounts();
        // all seasons after 31 should be "addSeasonDebts" - until last full season etc
        for (int i=31; i<CURRENT_SEASON-1; i++) {
            addSeasonDebts(i, debts);
        }
        addLastFullSeasonDebts(CURRENT_SEASON-1, debts);
        addCurrentSeasonDebts(CURRENT_SEASON, debts);

        return new TreeMap<>(debts.entrySet().stream().filter(e -> currentSquad.contains(e.getKey())).
                collect(Collectors.toMap(e -> e.getKey(), e->e.getValue())));
    }

    private void addSeasonDebts(int season, Map<String, TotalDebt> debts) {
        Map<String, Costs.Debt> costs = getCosts(season);
        costs.forEach((s, debt) -> {
            TotalDebt totalDebt = debts.computeIfAbsent(s, k -> new TotalDebt("0"));
            totalDebt.addDebt(debt.getTotalAsBigDecimal());
            debts.put(s, totalDebt);
        });
    }

    private void addLastFullSeasonDebts(int season, Map<String, TotalDebt> debts) {
        Map<String, Costs.Debt> costs = getCosts(season);
        costs.forEach((s, debt) -> {
            TotalDebt totalDebt = debts.computeIfAbsent(s, k -> new TotalDebt("0"));
            totalDebt.addLastFullSeasonCost(debt.getTotalAsBigDecimal());
            debts.put(s, totalDebt);
        });
    }

    private void addCurrentSeasonDebts(int season, Map<String, TotalDebt> debts) {
        Map<String, Costs.Debt> costs = getCosts(season);
        costs.forEach((s, debt) -> {
            TotalDebt totalDebt = debts.computeIfAbsent(s, k -> new TotalDebt("0"));
            totalDebt.addCurrentSeasonCost(debt.getTotalAsBigDecimal());
            debts.put(s, totalDebt);
        });
    }

    private static class MatchComparator implements Comparator<Match> {
        @Override
        public int compare(Match o1, Match o2) {
            int comp = compareInt(o1.getSeasonNumber(), o2.getSeasonNumber());
            if (comp == 0) {
                comp = compareInt(o1.getNumber(), o2.getNumber());
            }
            return comp;
        }

        private int compareInt(int i1, int i2) {
            return (i1 == i2)  ? 0 :  (i1 > i2 ? -1 : 1);
        }
    }
}
