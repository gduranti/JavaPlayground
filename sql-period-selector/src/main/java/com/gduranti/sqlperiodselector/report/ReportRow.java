package com.gduranti.sqlperiodselector.report;

import java.util.List;

import com.gduranti.sqlperiodselector.column.ColumnValue;

public class ReportRow implements Comparable<ReportRow> {

    private GenericKey key;
    private List<ColumnValue<?>> values;

    public ReportRow(GenericKey key, List<ColumnValue<?>> values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        values.stream().forEach(v -> builder.append(v.getValue()).append(","));
        return builder.toString();
    }

    @Override
    public int compareTo(ReportRow o) {
        return key.compareTo(o.key);
    }

}
