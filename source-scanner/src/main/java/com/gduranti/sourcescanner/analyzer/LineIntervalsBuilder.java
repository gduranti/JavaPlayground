package com.gduranti.sourcescanner.analyzer;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LineIntervalsBuilder {

    private final int lineLimit;

    public LineIntervalsBuilder(int lineLimit) {
        this.lineLimit = lineLimit;
    }

    public List<LineInterval> buildIntervals(List<SourceLine> matchingLines, int fileLineCount) {
        
        List<LineInterval> intervals = matchingLines.stream()
                                                    .map(x -> buildInterval(x, fileLineCount))
                                                    .sorted()
                                                    .collect(Collectors.toList());
        mergeIntervals(intervals);

        return intervals;
    }

    private LineInterval buildInterval(SourceLine sourceLine, int fileLineCount) {
        int initialLine = Math.max(sourceLine.getLineIndex() - lineLimit, 0);
        int finalLine = Math.min(sourceLine.getLineIndex() + lineLimit, fileLineCount - 1);
        return new LineInterval(initialLine, finalLine);
    }

    private void mergeIntervals(List<LineInterval> intervals) {
        if (intervals.isEmpty()) {
            return;
        }

        Iterator<LineInterval> iterator = intervals.iterator();
        LineInterval actualInterval = iterator.next();
        while (iterator.hasNext()) {
            LineInterval nextInterval = iterator.next();

            if (actualInterval.conflictsWith(nextInterval)) {
                actualInterval.mergeWith(nextInterval);
                iterator.remove();
            } else {
                actualInterval = nextInterval;
            }
        }
    }

}
