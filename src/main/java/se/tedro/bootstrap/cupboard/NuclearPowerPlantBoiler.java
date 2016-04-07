package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.EarlyDependencies;
import se.tedro.bootstrap.api.Water;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Nuclear
public class NuclearPowerPlantBoiler implements Boiler {
    private final ScheduledExecutorService scheduler;

    @Inject
    public NuclearPowerPlantBoiler(final ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public CompletableFuture<Water> boil(final Water water) {
        System.out.println("Boiling...: " + this);

        final CompletableFuture<Water> boiled = new CompletableFuture<>();

        scheduler.schedule(() -> boiled.complete(new Water(100, water.getAmount())), 10,
            TimeUnit.MILLISECONDS);

        return boiled;
    }

    CompletableFuture<Void> start() {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        scheduler.schedule(() -> {
            System.out.println("Nuclear power plant started: " + this);
            future.complete(null);
        }, 1, TimeUnit.SECONDS);

        return future;
    }

    public static class Config implements Boiler.Config {
        public Config() {
        }

        @Override
        public BoilerProvides provide(final EarlyDependencies dependencies) {
            return DaggerNuclearPowerPlantBoilerProvides
                .builder()
                .earlyDependencies(dependencies)
                .build();
        }
    }
}
