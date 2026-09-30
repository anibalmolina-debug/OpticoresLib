package com.opticores.lib.sync;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ponytail: simple monotonic frame counter.
 */
public class FrameId {
    private static final AtomicLong current = new AtomicLong(0);

    public static long next() {
        return current.getAndIncrement();
    }

    public static long get() {
        return current.get();
    }

    public static void reset() {
        current.set(0);
    }
}
