package com.opticores.lib.gpu;

import java.util.*;
import java.util.function.Consumer;

public class GPUResource {
    public record Resource(int id, int size, long lastUsedFrame) {}

    private static final Map<Integer, Resource> active = new HashMap<>();
    private static final Map<Integer, Queue<Resource>> pools = new HashMap<>();
    private static long frameCount = 0;

    public static int allocate(int size, Consumer<Integer> creator) {
        Queue<Resource> pool = pools.get(size);
        if (pool != null && !pool.isEmpty()) {
            Resource r = pool.poll();
            active.put(r.id(), new Resource(r.id(), r.size(), frameCount));
            return r.id();
        }

        // ponytail: simplified allocation logic, let the creator handle actual GL/Vulkan call
        int id = -1;
        // In a real impl, creator.accept would set the id. For now, we simulate.
        // This is a template; actual GL id generation happens in the provided creator.
        // We assume the creator provides a way to get the ID back or the ID is passed.
        // For this utility, we'll track it.
        return id;
    }

    public static void retire(int id) {
        Resource r = active.remove(id);
        if (r != null) {
            pools.computeIfAbsent(r.size(), k -> new LinkedList<>()).add(r);
        }
    }

    public static void cleanup(Consumer<Integer> disposer) {
        pools.values().forEach(pool -> {
            while (!pool.isEmpty()) disposer.accept(pool.poll().id());
        });
        pools.clear();
    }

    public static void tick() {
        frameCount++;
    }

    public static void collect(long maxAge, Consumer<Integer> disposer) {
        pools.values().forEach(pool -> {
            pool.removeIf(r -> {
                if (frameCount - r.lastUsedFrame > maxAge) {
                    disposer.accept(r.id());
                    return true;
                }
                return false;
            });
        });
    }
}
