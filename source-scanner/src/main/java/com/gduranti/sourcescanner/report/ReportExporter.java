package com.gduranti.sourcescanner.report;

import com.gduranti.sourcescanner.ScannerReport;

public interface ReportExporter {

    void export(ScannerReport report, String destinationPath);

}
