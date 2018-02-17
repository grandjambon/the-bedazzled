package com.pj.bedazzled.data.filters;

import com.pj.bedazzled.data.MatchFilter;
import com.pj.bedazzled.data.model.Grade;
import com.pj.bedazzled.data.model.Match;

/**
 * Was the match played at the same level as our grade?
 */
public class GradeMatchFilter implements MatchFilter {

    private final Grade grade;

    public GradeMatchFilter(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean include(Match match) {
        return match.getGrade() == grade;
    }
}
