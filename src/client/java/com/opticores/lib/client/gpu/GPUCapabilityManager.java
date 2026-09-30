package com.opticores.lib.client.gpu;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GPUCapabilityManager {
    public enum State { SUPPORTED, UNAVAILABLE, DISABLED, FAILED }
    public record Feature(String id, State state, String detail) {}

    private static final Map<String, Feature> cache = new ConcurrentHashMap<>();

    public static State getFeatureState(String featureId, java.util.function.Supplier<Boolean> check) {
        return cache.computeIfAbsent(featureId, id -> {
            try {
                return new Feature(id, check.get() ? State.SUPPORTED : State.UNAVAILABLE, "");
            } catch (Exception e) {
                return new Feature(id, State.FAILED, e.getMessage());
            }
        }).state();
    }

    public static void setDisabled(String featureId) {
        cache.put(featureId, new Feature(featureId, State.DISABLED, "User disabled"));
    }

    public static Map<String, Feature> getProfile() {
        return Collections.unmodifiableMap(cache);
    }
}
