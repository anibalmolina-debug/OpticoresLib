package com.opticores.lib.util;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class FrameBudget {
    private final long budgetNanos;
    private final LongAdder totalNanos = new LongAdder();
    private final LongAdder frameCount = new LongAdder();
    private final DoubleAdder smoothedTime = new DoubleAdder();

    // ponytail: simple EMA for smoothing
    private static final double ALPHA = 0.1;

    public FrameBudget(double budgetMs) {
        this.budgetNanos = (long) (budgetMs * 1_000_000.0);
    }

    public void record(long nanos) {
        totalNanos.add(nanos);
        frameCount.increment();

        synchronized (smoothedTime) {
            double current = smoothedTime.doubleValue();
            smoothedTime.add(nanos - (current + (1 - ALPHA) * current));
            // Note: Simplified EMA. For high precision, use a dedicated atomic double.
        }
    }

    public double getAverageMs() {
        long count = frameCount.sum();
        return count == 0 ? 0 : (totalNanos.sum() / (double) count) / 1_000_000.0;
    }

    public double getSmoothedMs() {
        return smoothedTime.doubleValue() / 1_000_000.0;
    }

    public double getBudgetUtilization() {
        return getSmoothedMs() / (budgetNanos / 1_000_000.0);
    }

    public boolean isOverBudget() {
        return getSmoothedMs() > (budgetNanos / 1_000_000.0);
    }
}
