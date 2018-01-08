package com.gduranti.sqlperiodselector.column;

public class CountSumColumnValue extends DefaultColumnValue<Long> {

    public CountSumColumnValue(String alias) {
        super(alias, Long.class);
        setValue(0L);
    }

    @Override
    public void acumulateValue(Long newValue) {
        setValue(getValue() + newValue);
    }

}
