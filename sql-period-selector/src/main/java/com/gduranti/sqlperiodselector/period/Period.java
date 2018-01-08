package com.gduranti.sqlperiodselector.period;

import java.time.LocalDate;

public interface Period {

    LocalDate getStart();

    LocalDate getEnd();

}
