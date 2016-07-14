package com.gduranti.sourcescanner;

import java.util.ArrayList;
import java.util.List;

import com.gduranti.sourcescanner.analyzer.AnalyzedFile;

public class ScannerReport {

    private List<AnalyzedFile> analyzedFiles = new ArrayList<>();
    private int analyzedLineCount;

    public List<AnalyzedFile> getSourceFiles() {
        return analyzedFiles;
    }

    public int getAnalyzedLineCount() {
        return analyzedLineCount;
    }

    public int lineAnalyzed() {
        return ++analyzedLineCount;
    }

    public void add(AnalyzedFile analyzedFile) {
        analyzedFiles.add(analyzedFile);
    }

}
