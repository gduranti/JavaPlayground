package com.gduranti.sourcescanner.report;

import java.io.File;

import com.gduranti.sourcescanner.ScannerReport;

class ConsoleReportExporter implements ReportExporter {

    @Override
    public File export(ScannerReport report, String destinationPath) {

        String content = new TxtReportExporter().buildText(report);
        System.out.println(content);

        return null;
    }


}
