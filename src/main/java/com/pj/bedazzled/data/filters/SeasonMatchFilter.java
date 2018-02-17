package com.pj.bedazzled.data.filters;

import com.pj.bedazzled.data.MatchFilter;
import com.pj.bedazzled.data.model.Match;

public class SeasonMatchFilter implements MatchFilter {


    @Override
    public boolean include(Match match) {
        return false;
    }
}
