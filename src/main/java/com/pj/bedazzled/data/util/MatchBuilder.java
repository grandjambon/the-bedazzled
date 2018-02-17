package com.pj.bedazzled.data.util;

import com.pj.bedazzled.data.model.Match;
import com.pj.bedazzled.data.model.MatchEvent;
import static com.pj.bedazzled.data.model.MatchEvent.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by phil on 05/02/17.
 */
public class MatchBuilder {
//    private Opponent opponent;

    private final int seasonNumber;
    private final int matchNumber;    // what match in the season are we?
    private String opponent;
    private String goalie;
    private List<String> players = new LinkedList<String>();
    private List<MatchEvent> matchEvents = new LinkedList<MatchEvent>();

    public MatchBuilder(int matchNumber, int seasonNumber) {
        this.matchNumber = matchNumber;
        this.seasonNumber = seasonNumber;
    }

    public MatchBuilder setOpponent(String opponentName) {

        this.opponent = opponentName;
        return this;
    }

    public MatchBuilder setGoalie(String goalie) {
        this.goalie = goalie;
        return this;
    }

    public MatchBuilder addPlayer(String player) {
        players.add(player);
        return this;
    }

    public MatchBuilder addGoal(String scorer) {
        matchEvents.add(getGoal(scorer));
        return this;
    }

    public MatchBuilder addHalfTime() {
        matchEvents.add(getHalfTime());
        return this;
    }

    public MatchBuilder addConceded() {
        matchEvents.add(getConceded());
        return this;
    }

    public Match build() {
        return new Match(seasonNumber, matchNumber, opponent, goalie, players, matchEvents);
    }

}
