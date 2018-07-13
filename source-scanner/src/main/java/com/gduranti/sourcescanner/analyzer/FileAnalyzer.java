package com.gduranti.sourcescanner.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.gduranti.sourcescanner.ScannerProperties;

public class FileAnalyzer {

    private ScannerProperties properties;
    private LineIntervalsBuilder lineIntervalsBuilder;

    public FileAnalyzer(ScannerProperties properties) {
        this.properties = properties;
        this.lineIntervalsBuilder = new LineIntervalsBuilder(properties.lineLimit);
    }

    public AnalyzedFile analyzeFile(File file) {
        System.out.println("Analyzing file " + file.getName());

        AnalyzedFile analyzedFile = new AnalyzedFile(file.getName(), file.getPath());

        List<SourceLine> fileLines = readLines(file);
        List<SourceLine> matchingLines = findMatchingLines(fileLines);
        List<LineInterval> intervals = lineIntervalsBuilder.buildIntervals(matchingLines, fileLines.size());

        loadFragments(analyzedFile, fileLines, intervals);

        return analyzedFile;
    }


    private List<SourceLine> findMatchingLines(List<SourceLine> fileLines) {
        List<SourceLine> matchingLines = new ArrayList<>();

        for (SourceLine line : fileLines) {
            if (!properties.ignoreComments || !isComment(line)) {
                boolean shouldAdd = analyzeLine(line, fileLines);
                if (shouldAdd) {
                    matchingLines.add(line);
                }
            }
        }

        return matchingLines;
    }

    @SuppressWarnings("unchecked")
    private List<SourceLine> readLines(File file) {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file));
            List<SourceLine> sourceLines = new ArrayList<>();
            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                String lineContent = lines.get(lineIndex);
                sourceLines.add(new SourceLine(lineIndex, lineContent));
            }
            return sourceLines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean analyzeLine(SourceLine sourceLine, List<SourceLine> lines) {
        for (String expression : properties.expressions) {
            Matcher m = Pattern.compile(".*(" + expression + ").*").matcher(sourceLine.getContent());
            if (m.matches()) {
                sourceLine.matchesAt(m.start(1), m.end(1));
            }
        }
        return sourceLine.hasMatches();
    }

    private boolean isComment(SourceLine sourceLine) {
        return sourceLine.getContent().startsWith(properties.language.getLineCommentPrefix());
    }

    private void loadFragments(AnalyzedFile analyzedFile, List<SourceLine> fileLines, List<LineInterval> intervals) {
        for (LineInterval lineInterval : intervals) {
            SouceFragment fragment = new SouceFragment();
            for (int i = lineInterval.getInitialLine(); i <= lineInterval.getFinalLine(); i++) {
                SourceLine sourceLine = fileLines.get(i);
                fragment.addLine(sourceLine);
            }
            analyzedFile.add(fragment);
        }
    }

}
