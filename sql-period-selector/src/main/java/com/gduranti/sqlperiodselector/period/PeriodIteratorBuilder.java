package com.gduranti.sqlperiodselector.period;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class PeriodIteratorBuilder {
    
    private LocalDate initialDate;
    private LocalDate finalDate;
    private SelectStrategy selectStrategy;

    public static PeriodIteratorBuilder lookupYear(int year) {
        return lookupFrom(LocalDate.of(year, Month.JANUARY, 1))
                .to(LocalDate.of(year, Month.DECEMBER, 31));
    }

    public static PeriodIteratorBuilder lookupFromYear(int year) {
        return lookupFrom(LocalDate.of(year, Month.JANUARY, 1));
    }

    public static PeriodIteratorBuilder lookupMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        return lookupFrom(start).to(start.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public static PeriodIteratorBuilder lookupFrom(LocalDate initialDate) {
        PeriodIteratorBuilder builder = new PeriodIteratorBuilder();
        builder.initialDate = initialDate;
        return builder;
    }

    public static PeriodIteratorBuilder lookupFrom(Integer initialDate) {
        PeriodIteratorBuilder builder = new PeriodIteratorBuilder();
        builder.initialDate = LocalDate.parse(initialDate.toString(), DateTimeFormatterFactory.createSamdFormatter());
        return builder;
    }

    public PeriodIteratorBuilder toYear(Integer year) {
        return to(LocalDate.of(year, Month.DECEMBER, 31));
    }

    public PeriodIteratorBuilder to(LocalDate finalDate) {
        this.finalDate = finalDate;
        return this;
    }

    public PeriodIteratorBuilder to(Integer finalDate) {
        return to(LocalDate.parse(finalDate.toString(), DateTimeFormatterFactory.createSamdFormatter()));
    }

    public PeriodIteratorBuilder selectingBy(SelectStrategy selectStrategy) {
        this.selectStrategy = selectStrategy;
        return this;
    }

    public PeriodIterator build() {
        return new PeriodIterator(initialDate, finalDate, selectStrategy);
    }

}
