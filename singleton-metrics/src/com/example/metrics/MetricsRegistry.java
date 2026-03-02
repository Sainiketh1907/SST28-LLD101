package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * REFACTORED: Global metrics registry - proper thread-safe Singleton.
 *
 * Implementation details:
 * - Private constructor with reflection protection
 * - Lazy initialization via static holder (Bill Pugh pattern)
 * - Thread-safe by JVM class loading guarantees
 * - Serialization protection via readResolve()
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    /**
     * Private constructor prevents direct instantiation and blocks reflection attacks.
     */
    private MetricsRegistry() {
        // Prevent reflection-based construction when instance already exists
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton instance already exists. Cannot create via reflection.");
        }
    }

    /**
     * Static holder pattern for lazy initialization.
     * The inner class is not loaded until getInstance() is called.
     * Thread-safe by JVM class loading mechanism.
     */
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    /**
     * Returns the singleton instance.
     * Thread-safe and lazy-initialized via static holder pattern.
     */
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    /**
     * Ensures that deserialization returns the same singleton instance
     * instead of creating a new object.
     */
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
