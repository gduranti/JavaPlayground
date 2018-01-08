package com.gduranti.sourcescanner.report;

import com.gduranti.sourcescanner.ScannerReport;
import com.gduranti.sourcescanner.analyzer.AnalyzedFile;
import com.gduranti.sourcescanner.analyzer.SouceFragment;
import com.gduranti.sourcescanner.analyzer.SourceLine;

class ConsoleReportExporter implements ReportExporter {

    @Override
    public void export(ScannerReport report, String destinationPath) {

        int fragments = 0;

        StringBuilder builder = new StringBuilder();

        for (AnalyzedFile analyzedFile : report.getSourceFiles()) {

            builder.append("File: " + analyzedFile.getName());
            builder.append("\n\n##############################################################################\n\n");

            for (SouceFragment fragment : analyzedFile.getFragments()) {
                fragments++;
                for (SourceLine sourceLine : fragment.getLines()) {
                    builder.append(sourceLine.getContent()).append("\n");
                }
                builder.append("\n\n##############################################################################\n\n");
            }
        }

        System.out.println("Files: " + report.getSourceFiles().size());
        System.out.println("Fragments: " + fragments);
        System.out.println(builder.toString());
    }

}
