package com.gduranti.sourcescanner;

import java.io.File;

import com.gduranti.sourcescanner.analyzer.FileAnalyzer;
import com.gduranti.sourcescanner.language.Language;

public class SourceScanner {

    private ScannerProperties properties;
    private FileAnalyzer fileAnalyzer;

    private SourceScanner() {
    }

    public ScannerReport analyze(String path) {
        ScannerReport report = new ScannerReport();
        analyze(report, new File(path));
        return report;
    }

    private void analyze(ScannerReport report, File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                analyze(report, subFile);
            }
        } else {
            report.add(fileAnalyzer.analyzeFile(file));
        }
    }

    public static class SourceScannerBuilder {

        private SourceScanner building;

        public static SourceScannerBuilder forLanguage(Language language) {
            SourceScannerBuilder builder = new SourceScannerBuilder();
            builder.building = new SourceScanner();
            builder.building.properties = new ScannerProperties();
            builder.building.properties.language = language;
            return builder;
        }

        public SourceScannerBuilder finding(String... expressions) {
            building.properties.expressions = expressions;
            return this;
        }

        public SourceScannerBuilder withLineLimit(int lineLimit) {
            building.properties.lineLimit = lineLimit;
            return this;
        }

        public SourceScannerBuilder ignoringComments() {
            building.properties.ignoreComments = true;
            return this;
        }

        public SourceScannerBuilder consideringComments() {
            building.properties.ignoreComments = false;
            return this;
        }

        public SourceScanner build() {
            building.fileAnalyzer = new FileAnalyzer(building.properties);
            return building;
        }
    }

}
