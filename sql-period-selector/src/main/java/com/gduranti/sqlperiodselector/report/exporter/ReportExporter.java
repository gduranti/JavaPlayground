package com.gduranti.sqlperiodselector.report.exporter;

import java.io.File;

import com.gduranti.sqlperiodselector.report.Report;

public interface ReportExporter {

    void export(Report report, File file);

}