package com.gduranti.sourcescanner;

import static com.gduranti.sourcescanner.language.Language.COBOL;

import com.gduranti.sourcescanner.SourceScanner.SourceScannerBuilder;
import com.gduranti.sourcescanner.report.ReportExporter;
import com.gduranti.sourcescanner.report.ReportExporterFactory;

public class SourceScannerMain {

    public static void main(String[] args) {

        SourceScanner scanner = SourceScannerBuilder.forLanguage(COBOL)
                .finding("tabela-tal*", "K000")
                .withLineLimit(3)
                .consideringComments()
                .build();

        ScannerReport report = scanner.analyze("C:\\caminho......");

        ReportExporter reporterExporter = ReportExporterFactory.create("console");
        reporterExporter.export(report, "C:\\caminhho.............");
    }

}
