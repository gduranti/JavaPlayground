package com.gduranti.sqlperiodselector.column;

public class ColumnValueFactory {

    public static ColumnValue<?> createFor(ColumnConfig columnConfig) {
        switch (columnConfig.getType()) {
            case KEY:
                return createKeyColumn(columnConfig.getAlias());
            case COUNT:
                return createCountColumn(columnConfig.getAlias());
            case SUM:
                return createSumColumn(columnConfig.getAlias());
            case MAX:
                return createMaxLongColumn(columnConfig.getAlias());
            case MIN:
                return createMinLongColumn(columnConfig.getAlias());
            default:
                throw new RuntimeException("Not implemented yet");
        }
    }

    public static ColumnValue<String> createKeyColumn(String alias) {
        return new DefaultColumnValue<String>(alias, String.class);
    }

    public static ColumnValue<Long> createCountColumn(String alias) {
        return new CountSumColumnValue(alias);
    }

    public static ColumnValue<Long> createSumColumn(String alias) {
        return new CountSumColumnValue(alias);
    }

    public static ColumnValue<Long> createMaxLongColumn(String alias) {
        return new MaxColumnValue<>(alias, Long.class);
    }

    public static ColumnValue<Long> createMinLongColumn(String alias) {
        return new MinColumnValue<>(alias, Long.class);
    }

}
