package com.gduranti.sqlperiodselector.period;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayToDayPeriod implements Period {

    private final LocalDate start;
    private final LocalDate end;

    public DayToDayPeriod(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public DayToDayPeriod(Integer start, Integer end) {
        DateTimeFormatter formatter = DateTimeFormatterFactory.createSamdFormatter();
        this.start = LocalDate.parse(start.toString(), formatter);
        this.end = LocalDate.parse(end.toString(), formatter);
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatterFactory.createSamdFormatter();
        return start.format(formatter) + "-" + end.format(formatter);
    }

}