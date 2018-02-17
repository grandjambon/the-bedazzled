package com.pj.bedazzled.data.filters;

import com.pj.bedazzled.data.MatchFilter;
import com.pj.bedazzled.data.model.Match;

/**
 * includes everything
 */
public class TrivialMatchFilter implements MatchFilter {
    @Override
    public boolean include(Match match) {
        return true;
    }
}
