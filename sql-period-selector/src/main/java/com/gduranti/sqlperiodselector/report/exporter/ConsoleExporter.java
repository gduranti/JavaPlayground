package com.gduranti.sqlperiodselector.report.exporter;

import java.io.File;

import com.gduranti.sqlperiodselector.report.Report;

public class ConsoleExporter implements ReportExporter {

    @Override
    public void export(Report report, File file) {
        System.out.println("\n\n-------------\n Report \n-------------\n");
        System.out.println("\nProcessing Time: " + report.getProcessingTime().getSeconds() + " seconds \n\n");
        System.out.println(ReportPrintFormatter.buildStringReport(report, ","));
    }

}
