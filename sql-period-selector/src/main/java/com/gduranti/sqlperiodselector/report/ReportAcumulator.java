package com.gduranti.sqlperiodselector.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gduranti.sqlperiodselector.column.ColumnConfig;
import com.gduranti.sqlperiodselector.column.ColumnType;
import com.gduranti.sqlperiodselector.column.ColumnValue;
import com.gduranti.sqlperiodselector.column.ColumnValueFactory;

public class ReportAcumulator {

    private List<ColumnConfig> columnsConfig;
    private Map<GenericKey, List<ColumnValue<?>>> resultMap;

    public ReportAcumulator() {
        this.resultMap = new HashMap<>();
        this.columnsConfig = new ArrayList<>();
    }

    public ReportAcumulator configColumn(String alias, ColumnType type) {
        columnsConfig.add(new ColumnConfig(alias, type));
        return this;
    }

    public void addResult(ResultSet resultSet) {

        List<ColumnValue<?>> rowValues = buildInitialColumnValues();
        for (ColumnValue<?> columnValue : rowValues) {
            acumulateValue(resultSet, columnValue);
        }

        GenericKey rowKey = buildKey(rowValues);

        List<ColumnValue<?>> existingRowValues = resultMap.get(rowKey);
        if (existingRowValues == null) {
            resultMap.put(rowKey, rowValues);
        } else {
            for (ColumnValue<?> columnValue : existingRowValues) {
                acumulateValue(resultSet, columnValue);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void acumulateValue(ResultSet resultSet, ColumnValue<T> columnValue) {
        T value = (T) extractValue(resultSet, columnValue);
        columnValue.acumulateValue(value);
    }

    private Object extractValue(ResultSet resultSet, ColumnValue<?> columnValue) {
        try {
            if (columnValue.getType().equals(String.class)) {
                return resultSet.getString(columnValue.getAlias());
            } else if (columnValue.getType().equals(Long.class)) {
                return resultSet.getLong(columnValue.getAlias());
            } else {
                throw new RuntimeException("Type not expected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private GenericKey buildKey(List<ColumnValue<?>> rowValues) {
        List<String> aliasKey = columnsConfig.stream()
                .filter(c -> c.is(ColumnType.KEY))
                .map(c -> c.getAlias())
                .collect(Collectors.toList());

        GenericKey key = new GenericKey();
        rowValues.stream()
                .filter(c -> aliasKey.contains(c.getAlias()))
                .forEach(c -> key.addKey(c.getValue().toString()));
        return key;
    }

    private List<ColumnValue<?>> buildInitialColumnValues() {
        return columnsConfig.stream()
                .map(c -> ColumnValueFactory.createFor(c))
                .collect(Collectors.toList());
    }

    public Report buildReport(Duration processingTime) {
        Report report = new Report();
        report.addProcessingTime(processingTime);
        columnsConfig.stream().forEach(c -> report.addHeader(c.getAlias()));
        resultMap.entrySet().stream().forEach(e -> report.addRow(new ReportRow(e.getKey(), e.getValue())));
        return report;
    }

}
