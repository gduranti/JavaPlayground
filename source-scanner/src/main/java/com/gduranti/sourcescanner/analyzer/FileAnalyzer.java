package com.gduranti.sourcescanner.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.gduranti.sourcescanner.ScannerProperties;
import com.gduranti.sourcescanner.language.Command;

public class FileAnalyzer {

    private ScannerProperties properties;
    private Command workingCommand;

    public FileAnalyzer(ScannerProperties properties) {
        this.properties = properties;
    }

    @SuppressWarnings("unchecked")
    public AnalyzedFile analyzeFile(File file) {

        AnalyzedFile analyzedFile = new AnalyzedFile(file.getName(), file.getPath());

        List<SourceLine> matchingLines = new ArrayList<>();

        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file));

            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                String line = lines.get(lineIndex);
                SourceLine sourceLine = new SourceLine(lineIndex, line);
                if (!properties.ignoreComments || !isComment(sourceLine)) {
                    boolean shouldAdd = analyzeLine(sourceLine, lines);
                    if (shouldAdd) {
                        matchingLines.add(sourceLine);
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private boolean analyzeLine(SourceLine sourceLine, List<String> lines) {

        for (String expression : properties.expressions) {
            Matcher m = Pattern.compile(expression).matcher(sourceLine.getContent());
            if (m.matches()) {
                sourceLine.matchesAt(m.start(), m.end());
            }
        }

        boolean shouldAdd = false;
        if (sourceLine.hasMatches() || workingCommand != null) {
            if (workingCommand == null && !isComment(sourceLine)) {
                workingCommand = identifyCommand(sourceLine.getContent(), lines);
            }
            shouldAdd = true;
        }

        if (workingCommand != null && commandWasClosedAt(sourceLine)) {
            workingCommand = null;
        }

        return shouldAdd;
    }

    private Command identifyCommand(String content, List<String> lines) {
        for (Command command : properties.language.getCommands()) {
            if (content.contains(command.getName())) {
                return command;
            }
        }

        return null;
    }

    private boolean commandWasClosedAt(SourceLine sourceLine) {
        for (String marker : workingCommand.getCloseMarkers()) {
            if (sourceLine.getContent().contains(marker)) {
                return true;
            }
        }
        return false;
    }

    private boolean isComment(SourceLine sourceLine) {
        return sourceLine.getContent().startsWith(properties.language.getLineCommentPrefix());
    }

}
