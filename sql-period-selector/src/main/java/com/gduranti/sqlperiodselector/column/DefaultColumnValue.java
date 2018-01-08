package com.gduranti.sqlperiodselector.column;

public class DefaultColumnValue<T> implements ColumnValue<T> {

    private final String alias;
    private T value;
    private Class<T> type;

    public DefaultColumnValue(String alias, Class<T> type) {
        this.alias = alias;
        this.type = type;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public Class<T> getType() {
        return type;
    }


    @Override
    public T getValue() {
        return value;
    }
    public void acumulateValue(T value) {
        setValue(value);
    }

    final void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getAlias() + ": " + getValue();
    }

}