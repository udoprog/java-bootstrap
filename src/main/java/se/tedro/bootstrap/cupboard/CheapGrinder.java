package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.CoffeeBeans;
import se.tedro.bootstrap.api.Grinder;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CheapGrinder implements Grinder {
    private final ScheduledExecutorService scheduler;

    @Inject
    public CheapGrinder(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public CompletableFuture<CoffeeBeans> grind(final int cups) {
        final CompletableFuture<CoffeeBeans> future = new CompletableFuture<>();

        // cheap ones are slow
        scheduler.schedule(() -> future.complete(new CoffeeBeans(cups)), 1, TimeUnit.SECONDS);

        return future;
    }
}
