package com.opticores.lib.api;

import java.util.Optional;

public interface FeatureState<T> {
    Optional<T> value();
    FailureReason failure();
    boolean isEnabled();

    record FailureReason(String code, String message) {}

    static <T> FeatureState<T> enabled(T value) {
        return new State<>(Optional.of(value), null);
    }

    static <T> FeatureState<T> disabled(String code, String message) {
        return new State<>(Optional.empty(), new FailureReason(code, message));
    }

    record State<T>(Optional<T> value, FailureReason failure) implements FeatureState<T> {
        @Override public boolean isEnabled() { return value.isPresent(); }
    }
}
