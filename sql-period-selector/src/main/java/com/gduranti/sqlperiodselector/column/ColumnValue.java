package com.gduranti.sqlperiodselector.column;

public interface ColumnValue<T> {

    public String getAlias();

    public Class<T> getType();

    public T getValue();

    public void acumulateValue(T t);

}