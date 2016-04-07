package se.tedro.bootstrap;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface LifeCycleRegistry {
    void start(Supplier<CompletableFuture<Void>> hook);

    void stop(Supplier<CompletableFuture<Void>> hook);
}
