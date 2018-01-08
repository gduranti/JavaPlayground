package com.gduranti.sourcescanner.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class SourceLine {

    private final Integer lineIndex;
    private final String content;
    private final List<Pair<Integer, Integer>> matchingExpressions;

    public SourceLine(Integer lineIndex, String content) {
        this.lineIndex = lineIndex;
        this.content = content;
        this.matchingExpressions = new ArrayList<>();
    }

    public Integer getLineIndex() {
        return lineIndex;
    }

    public String getContent() {
        return content;
    }

    public void matchesAt(Integer from, Integer to) {
        matchingExpressions.add(ImmutablePair.of(from, to));
    }

    public List<Pair<Integer, Integer>> getMatchingExpressions() {
        return matchingExpressions;
    }

    public boolean hasMatches() {
        return !matchingExpressions.isEmpty();
    }

    @Override
    public String toString() {
        return "SouceLine #" + lineIndex + " matches: " + hasMatches();
    }

}
