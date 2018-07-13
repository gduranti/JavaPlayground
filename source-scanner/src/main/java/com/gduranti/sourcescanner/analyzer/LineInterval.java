package com.gduranti.sourcescanner.analyzer;

public class LineInterval implements Comparable<LineInterval> {

    private int initialLine;
    private int finalLine;

    public LineInterval(int initialLine, int finalLine) {
        this.initialLine = initialLine;
        this.finalLine = finalLine;
    }

    public int getInitialLine() {
        return initialLine;
    }

    public int getFinalLine() {
        return finalLine;
    }

    public void mergeWith(LineInterval nextInterval) {
        finalLine = nextInterval.finalLine;
    }
    
    public boolean conflictsWith(LineInterval nextInterval) {
        return finalLine >= nextInterval.initialLine;
    }

    @Override
    public int compareTo(LineInterval other) {
        int compare = Integer.compare(initialLine, other.initialLine);
        if (compare == 0) {
            compare = Integer.compare(finalLine, other.finalLine);
        }
        return compare;
    }

    @Override
    public String toString() {
        return "LineInterval " + initialLine + "-" + finalLine;
    }

}
