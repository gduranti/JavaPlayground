package com.gduranti.sqlperiodselector.report;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.gduranti.sqlperiodselector.report.exporter.ConsoleExporter;
import com.gduranti.sqlperiodselector.report.exporter.ReportExporter;

public class Report {

    private Duration processingTime;
    private final List<String> headers;
    private final List<ReportRow> rows;

    public Report() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public void addProcessingTime(Duration processingTime) {
        this.processingTime = processingTime;
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void addRow(ReportRow row) {
        rows.add(row);
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<ReportRow> getRows() {
        return rows;
    }

    public Duration getProcessingTime() {
        return processingTime;
    }

    public void print() {
        export(new ConsoleExporter(), null);
    }

    public void export(ReportExporter exporter, File file) {
        exporter.export(this, file);
    }

}
