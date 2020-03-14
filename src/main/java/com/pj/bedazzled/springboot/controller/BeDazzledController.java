package com.pj.bedazzled.springboot.controller;

import com.pj.bedazzled.data.BeDazzledDataManager;
import com.pj.bedazzled.data.MatchFilter;
import com.pj.bedazzled.data.Streak;
import com.pj.bedazzled.data.filters.GradeMatchFilter;
import com.pj.bedazzled.data.filters.TrivialMatchFilter;
import com.pj.bedazzled.data.model.*;
import com.pj.bedazzled.data.model.ui.UiMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pj.bedazzled.data.BeDazzledDataManager.CURRENT_SEASON;
import static com.pj.bedazzled.data.model.Grade.A_GRADE;
import static com.pj.bedazzled.data.model.Grade.B_GRADE;

@SuppressWarnings("unused")
@Controller
class BeDazzledController {

    @Autowired
    private BeDazzledDataManager dataManager;

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title", "The BeDazzled");
        modal.addAttribute("message", "GO GO GO!");
        return "hello";
    }

    /**
     *
     * @param grade a, b, t, all
     * @param num 0 for everything, obv
     * @throws IOException if we get an error loading a file
     */
    @RequestMapping("/appearances/grade/{grade}/minapps/{num}")
    String appearances(@PathVariable String grade, @PathVariable @NumberFormat int num, ModelMap model) throws IOException {

        MatchFilter filter;

        switch (grade) {
            case "a":
                filter = new GradeMatchFilter(A_GRADE);
                break;
            case "b":
                filter = new GradeMatchFilter(B_GRADE);
                break;
            case "t":
                filter = new GradeMatchFilter(Grade.TRANSITION);
                break;
            default:
                filter = new TrivialMatchFilter();
                break;
        }


        Collection<Player> basePlayers = dataManager.getPlayers(filter);
        Collection<Player> playersWithEnoughApps =
                basePlayers.stream().filter(p -> (p.getGoalieApps() + p.getApps()) >= num).
                        collect(Collectors.toList());

        model.addAttribute("players", playersWithEnoughApps);
        return "appearances";
    }

    @RequestMapping("/foundingfathers")
    String appearancesFoundingFathers(ModelMap model) throws IOException {

        final List<String> foundingFathers = new ArrayList<>(8);
        foundingFathers.add("Damian");
        foundingFathers.add("Magnus");
        foundingFathers.add("Nathan");
        foundingFathers.add("Pete");
        foundingFathers.add("Dominik");
        foundingFathers.add("Louis");

        Collection<Player> foundingFathersApps =
                dataManager.getPlayers().stream().filter(p -> (foundingFathers.contains(p.getName()))).
                        collect(Collectors.toList());
        model.addAttribute("players", foundingFathersApps);
        return "appearances";

    }

    @RequestMapping("/history")
    String history(ModelMap modal) throws IOException {
        modal.addAttribute("seasons", dataManager.getSeasons());
        return "history";
    }

    @RequestMapping(value = "/season/{seasonNum}")
    String season(@PathVariable @NumberFormat int seasonNum, ModelMap model) throws IOException {
        String seasonName = String.format("season%02d", seasonNum);
        Map<String, Match> matchesForSeason = dataManager.getMatchesForSeason(seasonName);
        List<Match> matches = new LinkedList<>(matchesForSeason.values());
        model.addAttribute("season", seasonNum);
        model.addAttribute("matches", matches);

        List<Player> apps = new ArrayList<>(dataManager.getPlayers(match -> match.getSeasonNumber() == seasonNum));
        apps.sort(Comparator.comparingInt(Player::getApps).reversed());
        apps.removeIf(player -> player.getApps() == 0);
        model.addAttribute("apps", apps);

        List<Player> goals = new ArrayList<>(dataManager.getPlayers(match -> match.getSeasonNumber() == seasonNum));
        goals.sort(Comparator.comparingInt(Player::getGoals).reversed());
        goals.removeIf(player -> player.getGoals() == 0);
        model.addAttribute("goals", goals);

        List<Player> goalies = new ArrayList<>(dataManager.getPlayers(match -> match.getSeasonNumber() == seasonNum));
        goalies.sort(Comparator.comparingInt(Player::getGoalieApps).reversed());
        goalies.removeIf(player -> player.getGoalieApps() == 0);
        model.addAttribute("gks", goalies);

        model.addAttribute("costs", dataManager.getCosts(seasonNum));

        return "season";
    }

    /**
     * All a bit messy - suspect all this logic should really be elsewhere
     */
    @RequestMapping(value="/season/{seasonNum}/match/{matchNum}")
    String match(@PathVariable @NumberFormat int seasonNum, @PathVariable @NumberFormat int matchNum, ModelMap model) {
        String seasonName = String.format("season%02d", seasonNum);
        Map<String, Match> matchesForSeason = dataManager.getMatchesForSeason(seasonName);

        String matchName = String.format("match-%02d-report.txt", matchNum);

        Match match = matchesForSeason.get(matchName);

        UiMatch uiMatch = new UiMatch(match.getOpponent(), matchNum, seasonNum, match.isForfeit());

        int gFor = 0;
        int gAgainst = 0;

        for (MatchEvent event : match.getMatchEvents()) {
            switch (event.getType()){
                case GOAL:
                    gFor++;
                    uiMatch.addGoal(String.format("%s-%s", gFor, gAgainst), "("+event.getScorer()+")");
                    break;
                case CONCEDED:
                    gAgainst++;
                    uiMatch.addGoal(String.format("%s-%s", gFor, gAgainst), "");
                    break;
                case HT:
                    uiMatch.addHalfTime(String.format("%s-%s", gFor, gAgainst));
                    break;
            }
        }

        model.addAttribute("match", uiMatch);
        model.addAttribute("goalie", match.getGoalie());
        model.addAttribute("outfielders", match.getPlayers());

        return "match";
    }

    @RequestMapping(value="/records")
    String records(ModelMap model) throws IOException {
        // get goalie apps
        List<Player> goalies = new ArrayList<>(dataManager.getPlayers());
        goalies.sort(Comparator.comparingInt(Player::getGoalieApps).reversed());
        model.addAttribute("goalieApps", goalies.subList(0,9));

        // get appearances
        List<Player> players = new ArrayList<>(dataManager.getPlayers());
        players.sort(Comparator.comparingInt(Player::getApps).reversed());
        model.addAttribute("apps", players.subList(0,9));
        // a grade
        List<Player> aGradePlayers = new ArrayList<>(dataManager.getPlayers(new GradeMatchFilter(A_GRADE)));
        aGradePlayers.sort(Comparator.comparingInt(Player::getApps).reversed());
        model.addAttribute("aGradeApps", aGradePlayers.subList(0,9));
        // b grade
        List<Player> bGradePlayers = new ArrayList<>(dataManager.getPlayers(new GradeMatchFilter(B_GRADE)));
        bGradePlayers.sort(Comparator.comparingInt(Player::getApps).reversed());
        model.addAttribute("bGradeApps", bGradePlayers.subList(0,9));
        // get top scorers
        List<Player> goals = new ArrayList<>(dataManager.getPlayers());
        goals.sort(Comparator.comparingInt(Player::getGoals).reversed());
        model.addAttribute("goals", goals.subList(0,9));
        // a grade
        List<Player> aGradePlayersForGoals = new ArrayList<>(dataManager.getPlayers(new GradeMatchFilter(A_GRADE)));
        aGradePlayersForGoals.sort(Comparator.comparingInt(Player::getGoals).reversed());
        model.addAttribute("aGradeGoals", aGradePlayersForGoals.subList(0,9));
        // b grade
        List<Player> bGradePlayersForGoals = new ArrayList<>(dataManager.getPlayers(new GradeMatchFilter(B_GRADE)));
        bGradePlayersForGoals.sort(Comparator.comparingInt(Player::getGoals).reversed());
        model.addAttribute("bGradeGoals", bGradePlayersForGoals.subList(0,9));

        // appearance streaks

        // goalscoring streaks

        return "records";
    }

    @RequestMapping(value="/opponents")
    String opponents(ModelMap model) throws IOException {
        model.addAttribute("opponents", dataManager.getOpponentRecords(dataManager.getOpponents()));
        return "opponents";
    }

    @RequestMapping(value="/opponent/{opponent}")
    String opponent(@PathVariable String opponent, ModelMap model) throws IOException {
        model.addAttribute("matches", dataManager.getMatchesAgainstOpponent(opponent));

        List<Player> apps = new ArrayList<>(dataManager.getPlayers(match -> match.getOpponent().equals(opponent)));
        apps.sort(Comparator.comparingInt(Player::getApps).reversed());
        apps.removeIf(player -> player.getApps() == 0);
        model.addAttribute("apps", apps);

        List<Player> goals = new ArrayList<>(dataManager.getPlayers(match -> match.getOpponent().equals(opponent)));
        goals.sort(Comparator.comparingInt(Player::getGoals).reversed());
        goals.removeIf(player -> player.getGoals() == 0);
        model.addAttribute("goals", goals);

        List<Player> goalies = new ArrayList<>(dataManager.getPlayers(match -> match.getOpponent().equals(opponent)));
        goalies.sort(Comparator.comparingInt(Player::getGoalieApps).reversed());
        goalies.removeIf(player -> player.getGoalieApps() == 0);
        model.addAttribute("gks", goalies);

        return "opponent";
    }

    @RequestMapping(value = "/player/{player}")
    String player(@PathVariable String player, ModelMap modelMap) {
        Collection<PlayerMatch> playerMatches = dataManager.getPlayerMatches(player);
        Record record = dataManager.getRecordForPlayer(playerMatches);

        modelMap.put("name", player);
        modelMap.put("record", record);
        modelMap.put("playerMatches", playerMatches);
        return "player";
    }

    @RequestMapping(value="/walkup")
    String walkup(ModelMap model) throws IOException {
        return "walkup";
    }


    @RequestMapping(value="/streaks")
    String streaks(ModelMap model) throws IOException {
        Collection<Streak> appStreaks = dataManager.getAppStreaks(10);
        model.addAttribute("appStreaks", appStreaks);
        return "streaks";
    }

    @RequestMapping(value="/accounts")
    String accounts(ModelMap model) throws IOException {
        Map<String, TotalDebt> accounts = dataManager.getAccounts();
        model.addAttribute("accounts", accounts);
        model.addAttribute("lastFullSeason", CURRENT_SEASON-1);
        model.addAttribute("currentSeason", CURRENT_SEASON);
        return "accounts";
    }

}
