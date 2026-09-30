package com.opticores.lib.diagnostics;
import org.slf4j.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Diagnostics {
    private static final Map<String, AtomicInteger> ERROR_COUNTS = new ConcurrentHashMap<>();
    private static final int LIMIT = 10;

    public static void error(Logger logger, String message, Throwable cause) {
        String key = message + (cause != null ? cause.getClass().getName() : "");
        int count = ERROR_COUNTS.computeIfAbsent(key, k -> new AtomicInteger(0)).incrementAndGet();

        if (count <= LIMIT) {
            logger.error("[{}] {}", count, message, cause);
        } else if (count == LIMIT + 1) {
            logger.error("Further occurrences of [{}] suppressed", message);
        }
    }

    public static void log(Logger logger, String level, String tag, String message) {
        // ponytail: structured as simple string for SLF4J
        logger.info("[{}] [{}] {}", level, tag, message);
    }
}
