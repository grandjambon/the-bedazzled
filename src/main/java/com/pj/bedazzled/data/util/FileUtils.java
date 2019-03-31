package com.pj.bedazzled.data.util;

import com.pj.bedazzled.data.model.Match;
import com.pj.bedazzled.data.model.MatchEvent;
import com.pj.bedazzled.data.model.TotalDebt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import sun.management.FileSystem;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pj.bedazzled.data.model.MatchEvent.MatchEventType.GOAL;

public class FileUtils {

    public static final String BEDAZZLED_HOME = "classpath:seasons/";
    public static final String REPORTS = "/reports";
    public static final String CURRENT = "current";

    /**
     * @param season  must be of format "seasonXX" where XX is a 2 digit number *OR* "current"
     * @param number  must be a 2 digit number from 01 to 14 - depending on whether a season is 10, 11 or 14 games in length
     * @return a "Match" represented by a single file
     */
    public static Match getMatchForFile(String season, String number) throws IOException {
        String filename = "match-" + number + "-report.txt";

        InputStream stream = FileUtils.class.getResourceAsStream(BEDAZZLED_HOME + season + REPORTS + "/" + filename);

        // if report.exists() ?

        return getMatchForFile(season, Integer.parseInt(number), stream);
    }

    /**
     * @param season  must be of format "seasonXX" where XX is a 2 digit number *OR* "current"
     * @return a (Linked) List of "Matches" represented by the files in that season's directory
     */
    public static Map<String, Match> getSeasonForDirectory(String season) throws IOException {
        String resourcePath = BEDAZZLED_HOME + season + REPORTS + "/*";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources  = resolver.getResources(resourcePath);
        Map<String, Match> matches = new TreeMap<>();
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            Pattern compiledPattern = Pattern.compile("match-([0-9]{2})-report.txt");
            Matcher matcher = compiledPattern.matcher(filename);
            matcher.find();
            int matchNumber = Integer.parseInt(matcher.group(1));

            matches.put(filename, getMatchForFile(season, matchNumber, resource.getInputStream()));
        }
        return matches;
    }

    public static Collection<String> getSeasonNames() throws IOException {
        String resourcePath = BEDAZZLED_HOME+"*";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources  = resolver.getResources(resourcePath);

        Collection<String> seasons = new TreeSet<>();

        // there has to be a better way than this
        for (Resource resource : resources) {
            String path = null;
            if (resource instanceof ClassPathResource) {
                ClassPathResource cpcr = (ClassPathResource) resource;
                path = cpcr.getPath();
            } else if (resource instanceof FileSystemResource) {
                FileSystemResource fsr = (FileSystemResource) resource;
                path = fsr.getPath();
            }

            String[] pathElements = path.split("/");
            for (int i=pathElements.length; --i>=0; ) {
                if (pathElements[i].length() != 0) {
                    seasons.add(pathElements[i]);
                    break;
                }
            }
        }

        return seasons;
    }

    private static Match getMatchForFile(String seasonName, int matchNumber, InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        int seasonNumber = Integer.valueOf(seasonName.replace("season", ""));

        MatchBuilder builder = new MatchBuilder(matchNumber, seasonNumber);

        String s;
        while ((s = reader.readLine()) != null) {
            String[] parts = s.split(":");
            if (parts[0].equals("OPPONENT")) {
                builder = builder.setOpponent(parts[1]);
            } else if (parts[0].equals("GOALKEEPER")) {
                builder = builder.setGoalie(parts[1]);
            } else if (parts[0].equals("OUTFIELD")) {
                builder = builder.addPlayer(parts[1]);
            } else if (parts[0].equals("GOAL")) {
                builder = builder.addGoal(parts[1]);
            } else if (parts[0].equals("CONCEDED")){
                builder = builder.addConceded();
            } else if (parts[0].equals("HALF")) {
                builder = builder.addHalfTime();
            }
        }
        return builder.build();
    }

    public static Collection<String> currentSquadForAccounts() throws IOException {
        Collection<String> currentSquad = new ArrayList<>();

        InputStream stream = FileUtils.class.getResourceAsStream("/accounts-current-squad.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String s;
        while ((s = reader.readLine()) != null) {
            currentSquad.add(s);
        }
        return currentSquad;
    }

    public static Map<String, TotalDebt> getDebts() throws IOException {
        Map<String, TotalDebt> playerToAllTimeDebt = new HashMap<>();
        InputStream stream = FileUtils.class.getResourceAsStream("/baseline-debt.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String s;
        while ((s = reader.readLine()) != null) {
            if (s.startsWith("=")) {
                continue;
            }
            String[] parts = s.split("\\|");
            playerToAllTimeDebt.computeIfAbsent(parts[0], k -> new TotalDebt(parts[1]));
        }

        InputStream paymentsStream = FileUtils.class.getResourceAsStream("/payments.txt");
        BufferedReader paymentsReader = new BufferedReader(new InputStreamReader(paymentsStream));
        String payment;
        while ((payment = paymentsReader.readLine()) != null) {
            if (payment.startsWith("=") || payment.length() == 0) {
                continue;
            }
            String[] parts = payment.split("\\|");
            TotalDebt totalDebt = playerToAllTimeDebt.computeIfAbsent(parts[1], k -> new TotalDebt("0"));

            totalDebt.paymentReceived(parts[2]);
        }
        return playerToAllTimeDebt;
    }

    /**
     * Iterates across the full history, and returns a list of "Matches" that are included by the filter
     *
     * @param filter
     * @return
     */
    public static List<Match> getAllMatches(MatchFilter filter) {
        return null;
    }

    public interface MatchFilter {
        boolean includeMatch(Match match);
    }

    public static class AppearanceFilter implements MatchFilter {
        private final String name;

        public AppearanceFilter(String name) {
            this.name = name;
        }

        public boolean includeMatch(Match match) {
            return match.getPlayers().contains(name) || match.getGoalie().equals(name);
        }
    }

    public static class ScorerFilter implements MatchFilter {
        private final String name;

        public ScorerFilter(String name) {
            this.name = name;
        }

        public boolean includeMatch(Match match) {
            for (MatchEvent event : match.getMatchEvents()) {
                if (event.getType() == GOAL && event.getScorer().equals(name)) {
                    return true;
                }
            }
            return false;
        }

    }

}
