package com.gduranti.sqlperiodselector.period;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public enum SelectStrategy {

    DAY {
        public Period setup(LocalDate start, LocalDate finalLimit) {
            return new DayToDayPeriod(start, start);
        }

        public Period nextAfter(Period period, LocalDate finalLimit) {
            LocalDate nextDay = period.getEnd().plusDays(1);
            return new DayToDayPeriod(nextDay, nextDay);
        }
    },

    MONTH {
        public Period setup(LocalDate start, LocalDate finalLimit) {
            return new DayToDayPeriod(start, start.with(TemporalAdjusters.lastDayOfMonth()));
        }

        public Period nextAfter(Period period, LocalDate finalLimit) {
            LocalDate nextMonth = period.getStart().plusMonths(1);
            LocalDate start = nextMonth.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate end = nextMonth.with(TemporalAdjusters.lastDayOfMonth());

            if (end.isAfter(finalLimit)) {
                end = finalLimit;
            }

            return new DayToDayPeriod(start, end);
        }
    },

    YEAR {
        @Override
        public Period setup(LocalDate start, LocalDate finalLimit) {
            LocalDate end = start.with(TemporalAdjusters.lastDayOfYear());
            if (end.isAfter(finalLimit)) {
                end = finalLimit;
            }
            return new DayToDayPeriod(start, end);
        }

        @Override
        public Period nextAfter(Period period, LocalDate finalLimit) {
            LocalDate nextYear = period.getStart().plusYears(1);
            LocalDate start = nextYear.with(TemporalAdjusters.firstDayOfYear());
            LocalDate end = nextYear.with(TemporalAdjusters.lastDayOfYear());

            if (end.isAfter(finalLimit)) {
                end = finalLimit;
            }

            return new DayToDayPeriod(start, end);
        }
    }
    ;

    public abstract Period setup(LocalDate start, LocalDate finalLimit);

    public abstract Period nextAfter(Period period, LocalDate finalLimit);

}
