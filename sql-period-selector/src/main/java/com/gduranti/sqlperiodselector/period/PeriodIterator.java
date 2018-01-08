package com.gduranti.sqlperiodselector.period;

import java.time.LocalDate;
import java.util.Iterator;

public class PeriodIterator implements Iterator<Period> {

    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final SelectStrategy selectStrategy;
    private Period currentPeriod;

    public PeriodIterator(LocalDate initialDate, LocalDate finalDate, SelectStrategy selectStrategy) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.selectStrategy = selectStrategy;
    }

    @Override
    public boolean hasNext() {
        return currentPeriod == null || currentPeriod.getEnd().isBefore(finalDate);
    }

    @Override
    public Period next() {
        if (currentPeriod == null) {
            return currentPeriod = selectStrategy.setup(initialDate, finalDate);
        } else {
            return currentPeriod = selectStrategy.nextAfter(currentPeriod, finalDate);
        }
    }

}
