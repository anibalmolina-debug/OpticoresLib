package com.opticores.lib.util;

public class Timer {
    private long startNanos;

    public void start() {
        this.startNanos = System.nanoTime();
    }

    public long stopNanos() {
        return System.nanoTime() - startNanos;
    }

    public double stopMs() {
        return stopNanos() / 1_000_000.0;
    }

    public void record(FrameBudget budget) {
        budget.record(stopNanos());
    }
}
