package com.gduranti.sourcescanner.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public AnalyzedFile analyzeFile(File file) {

        AnalyzedFile analyzedFile = new AnalyzedFile(file.getName(), file.getPath());

        List<SourceLine> matchingLines = new ArrayList<>();

        try {
            List<SourceLine> lines = readLines(file);

            for (SourceLine line : lines) {
                if (!properties.ignoreComments || !isComment(line)) {
                    boolean shouldAdd = analyzeLine(line, lines);
                    if (shouldAdd) {
                        matchingLines.add(line);
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private List<SourceLine> readLines(File file) throws IOException, FileNotFoundException {
        List<String> lines = IOUtils.readLines(new FileInputStream(file));
        List<SourceLine> sourceLines = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            sourceLines.add(new SourceLine(lineIndex, line));
        }
        return sourceLines;
    }

    private boolean analyzeLine(SourceLine sourceLine, List<SourceLine> lines) {

        for (String expression : properties.expressions) {
            Matcher m = Pattern.compile(expression).matcher(sourceLine.getContent());
            if (m.matches()) {
                sourceLine.matchesAt(m.start(), m.end());
            }
        }

        boolean shouldAdd = false;
        if (sourceLine.hasMatches() || workingCommand != null) {
            if (workingCommand == null && !isComment(sourceLine)) {
                workingCommand = identifyCommand(sourceLine, lines);
            }
            shouldAdd = true;
        }

        if (workingCommand != null && commandWasClosedAt(sourceLine)) {
            workingCommand = null;
        }

        return shouldAdd;
    }

    private Command identifyCommand(SourceLine sourceLine, List<SourceLine> allLines) {
        for (Command command : properties.language.getCommands()) {
            if (sourceLine.getContent().contains(command.getName())) {
                return command;
            }
        }

        // int TODO aqui

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
