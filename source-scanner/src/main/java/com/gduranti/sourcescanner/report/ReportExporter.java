package com.gduranti.sourcescanner.report;

import java.io.File;

import com.gduranti.sourcescanner.ScannerReport;

public interface ReportExporter {

    File export(ScannerReport report, String destinationPath);

}
