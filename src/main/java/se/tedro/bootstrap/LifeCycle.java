package se.tedro.bootstrap;

import java.util.List;

public interface LifeCycle {
    void register(LifeCycleRegistry registry);

    /**
     * Combine multiple lifecycles into one.
     *
     * @param lifeCycles Lifecycles to combine.
     * @return A new lifecycle which is the combination of those provided.
     */
    static LifeCycle combine(List<LifeCycle> lifeCycles) {
        return registry -> {
            for (final LifeCycle l : lifeCycles) {
                l.register(registry);
            }
        };
    }

    /**
     * Create a lifecycle that does nothing.
     */
    static LifeCycle empty() {
        return registry -> {
        };
    }
}
