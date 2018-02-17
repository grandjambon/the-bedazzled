package com.pj.bedazzled.data;

import com.pj.bedazzled.data.model.Match;

public interface MatchFilter {
    boolean include(Match match);
}
