package com.gduranti.sqlperiodselector.report.exporter;

import java.io.File;

import com.gduranti.sqlperiodselector.report.Report;

public class ConsoleExporter implements ReportExporter {

    @Override
    public void export(Report report, File file) {
        System.out.println("\n\n-------------\n Report \n-------------\n");
        System.out.println(buildStringReport(report));
    }

    private String buildStringReport(Report report) {
        StringBuilder builder = new StringBuilder();

        report.getHeaders().stream().forEach(c -> builder.append(c).append(","));
        builder.append("\n");

        if (report.getRows().isEmpty()) {
            builder.append("No records found!");
        } else {
            report.getRows().stream().sorted().forEach(r -> builder.append(r.toString()).append("\n"));
        }

        return builder.toString();
    }

}
