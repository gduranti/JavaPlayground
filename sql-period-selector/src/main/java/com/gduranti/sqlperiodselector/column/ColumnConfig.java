package com.gduranti.sqlperiodselector.column;

public class ColumnConfig {

    private final String alias;
    private final ColumnType type;

    public ColumnConfig(String alias, ColumnType type) {
        this.alias = alias;
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public ColumnType getType() {
        return type;
    }

    public boolean is(ColumnType otherType) {
        return type.equals(otherType);
    }

}
