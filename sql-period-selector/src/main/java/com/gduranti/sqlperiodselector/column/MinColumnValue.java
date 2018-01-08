package com.gduranti.sqlperiodselector.column;

public class MinColumnValue<T extends Comparable<T>> extends DefaultColumnValue<T> {

    public MinColumnValue(String alias, Class<T> type) {
        super(alias, type);
    }

    @Override
    public void acumulateValue(T value) {
        if (getValue() == null || getValue().compareTo(value) < 0) {
            setValue(value);
        }
    }

}
