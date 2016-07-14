package com.gduranti.sourcescanner.report;

public class ReportExporterFactory {

    public static ReportExporter create(String reportType) {

        if (reportType == null) {
            reportType = "console";
        }

        switch (reportType.toLowerCase()) {
            case "console": return new ConsoleReportExporter();
            // case "txt" : return new TxtReportExporter();
            // case "pdf" : return new PdfReportExporter();
            // case "odt" : return new OdtReportExporter();
            default       : throw new RuntimeException("Report exporter not found.");
        }
    }

}
