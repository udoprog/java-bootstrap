package se.tedro.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.EarlyDependencies;
import se.tedro.bootstrap.cupboard.ThermoPotBoiler;
import se.tedro.bootstrap.dagger.Config;
import se.tedro.bootstrap.dagger.DaggerEarlyComponent;
import se.tedro.bootstrap.dagger.DaggerMainComponent;
import se.tedro.bootstrap.dagger.EarlyComponent;
import se.tedro.bootstrap.dagger.MainComponent;
import se.tedro.bootstrap.dagger.MainModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CoffeeMakerDagger {
    public static void main(String[] argv) throws Exception {
        final EarlyComponent early = DaggerEarlyComponent.builder().build();

        final Config c = readConfig(early);

        // dynamically load boiler.
        final Boiler.BoilerProvides boiler =
            c.getBoiler().orElseGet(ThermoPotBoiler::defaultConfig).provide(early);

        final MainModule main = new MainModule(boiler);

        final MainComponent component =
            DaggerMainComponent.builder().earlyComponent(early).mainModule(main).build();

        component.life().register(early.registry());

        runAll(early.startup());
        CoffeeMaker.run(() -> component.brewer());
        runAll(early.shutdown());

        System.exit(0);
    }

    private static void runAll(final List<Supplier<CompletableFuture<Void>>> hooks)
        throws InterruptedException, java.util.concurrent.ExecutionException,
        java.util.concurrent.TimeoutException {

        final CompletableFuture[] all =
            hooks.stream().map(Supplier::get).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(all).get(10, TimeUnit.SECONDS);
    }

    private static Config readConfig(final EarlyDependencies early) throws IOException {
        final ObjectMapper m = early.config();

        try (final InputStream input = CoffeeMakerDagger.class
            .getClassLoader()
            .getResourceAsStream("config.yaml")) {
            Objects.requireNonNull(input);
            return m.readValue(input, Config.class);
        }
    }
}
