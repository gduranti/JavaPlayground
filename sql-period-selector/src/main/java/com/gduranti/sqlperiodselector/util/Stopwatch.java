package com.gduranti.sqlperiodselector.util;

import java.time.Duration;

public class Stopwatch {

    private long start;
    private long end;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        end = System.currentTimeMillis();
    }

    public Duration get() {
        return Duration.ofMillis(end - start);
    }

}
