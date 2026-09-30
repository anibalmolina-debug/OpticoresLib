package com.opticores.lib.api;

import java.util.function.Supplier;

public interface Fallback<T> {
    T resolve(FeatureState<T> state);

    static <T> Fallback<T> of(T defaultValue) {
        return state -> state.isEnabled() ? state.value().get() : defaultValue;
    }

    static <T> Fallback<T> of(Supplier<T> defaultValueSupplier) {
        return state -> state.isEnabled() ? state.value().get() : defaultValueSupplier.get();
    }
}
