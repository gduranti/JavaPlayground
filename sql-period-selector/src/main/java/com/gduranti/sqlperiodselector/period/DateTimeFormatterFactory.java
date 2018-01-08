package com.gduranti.sqlperiodselector.period;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterFactory {

    public static DateTimeFormatter createSamdFormatter() {
        return DateTimeFormatter.ofPattern("yyyyMMdd");
    }

}
