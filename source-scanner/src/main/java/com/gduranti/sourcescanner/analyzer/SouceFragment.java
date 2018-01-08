package com.gduranti.sourcescanner.analyzer;

import java.util.ArrayList;
import java.util.List;

public class SouceFragment {

    private List<SourceLine> lines;

    public SouceFragment() {
        lines = new ArrayList<>();
    }

    public void addLine(SourceLine line) {
        lines.add(line);
    }

    public List<SourceLine> getLines() {
        return lines;
    }

    private Integer getInitialLine() {
        return !lines.isEmpty() ? lines.get(0).getLineIndex() : null;
    }

    private Integer getFinalLine() {
        return !lines.isEmpty() ? lines.get(lines.size() - 1).getLineIndex() : null;
    }

    @Override
    public String toString() {
        return "SouceFragment " + getInitialLine() + "-" + getFinalLine();
    }

}
