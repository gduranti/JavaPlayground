package com.gduranti.sqlperiodselector.report.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gduranti.sqlperiodselector.report.Report;

public class CsvExporter implements ReportExporter {

    @Override
    public void export(Report report, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(ReportPrintFormatter.buildStringReport(report, ";"));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
