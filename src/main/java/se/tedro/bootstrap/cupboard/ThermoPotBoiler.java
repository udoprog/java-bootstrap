package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.EarlyDependencies;
import se.tedro.bootstrap.api.Water;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThermoPotBoiler implements Boiler {
    private final ScheduledExecutorService scheduler;

    @Inject
    public ThermoPotBoiler(final ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public CompletableFuture<Water> boil(final Water water) {
        final CompletableFuture<Water> boiled = new CompletableFuture<>();

        scheduler.schedule(() -> boiled.complete(new Water(100, water.getAmount())), 2000,
            TimeUnit.MILLISECONDS);

        return boiled;
    }

    public static Boiler.Config defaultConfig() {
        return new Config();
    }

    public static class Config implements Boiler.Config {
        public Config() {
        }

        @Override
        public BoilerProvides provide(final EarlyDependencies dependencies) {
            return DaggerThermoPotBoilerProvides.builder().earlyDependencies(dependencies).build();
        }
    }
}
