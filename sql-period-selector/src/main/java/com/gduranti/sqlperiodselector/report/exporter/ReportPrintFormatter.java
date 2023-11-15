package com.gduranti.sqlperiodselector.report.exporter;

import com.gduranti.sqlperiodselector.report.Report;

public class ReportPrintFormatter {

    public static String buildStringReport(Report report, String separator) {
        StringBuilder builder = new StringBuilder();

        report.getHeaders().stream().forEach(c -> builder.append(c).append(separator));
        builder.append("\n");

        if (report.getRows().isEmpty()) {
            builder.append("No records found!");
        } else {
            report.getRows().stream().sorted().forEach(r -> builder.append(r.toString(separator)).append("\n"));
        }

        return builder.toString();
    }

}
