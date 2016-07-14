package com.gduranti.sourcescanner;

import static com.gduranti.sourcescanner.language.Language.COBOL;

import com.gduranti.sourcescanner.SourceScanner.SourceScannerBuilder;
import com.gduranti.sourcescanner.report.ReportExporter;
import com.gduranti.sourcescanner.report.ReportExporterFactory;

public class SourceScannerMain {

    public static void main(String[] args) {

        SourceScanner scanner = SourceScannerBuilder.forLanguage(COBOL)
                .finding("KVIN81*", "K81-*")
                .withLineLimit(15)
                .consideringComments()
                .build();

        ScannerReport report = scanner.analyze("C:\\Java\\Projetos\\IED\\trunk\\FontesUnisys\\sistemas\\VIN\\731");

        ReportExporter reporterExporter = ReportExporterFactory.create("console");
        reporterExporter.export(report, "C:\\Java\\git\\JavaPlayground\\source-scanner\\src\\test\\resources");

    }

}
