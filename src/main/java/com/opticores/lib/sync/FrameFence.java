package com.opticores.lib.sync;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ponytail: minimal fence for frame synchronization.
 * Use for GPU-CPU sync or multi-threaded frame retirement.
 */
public class FrameFence {
    private final AtomicLong completedFrame = new AtomicLong(-1);
    private final ConcurrentLinkedQueue<Long> retirementQueue = new ConcurrentLinkedQueue<>();

    public void signalComplete(long frameId) {
        // ponytail: assumes monotonic increase; updates max completed frame
        completedFrame.accumulateAndGet(frameId, Math::max);
        retirementQueue.offer(frameId);
    }

    public boolean isCompleted(long frameId) {
        return completedFrame.get() >= frameId;
    }

    public void waitUntilCompleted(long frameId) {
        while (!isCompleted(frameId)) {
            Thread.onSpinWait();
        }
    }

    public long getCompletedFrame() {
        return completedFrame.get();
    }

    public Long pollRetiredFrame() {
        return retirementQueue.poll();
    }

    public void clearRetired(long frameId) {
        retirementQueue.remove(frameId);
    }
}
