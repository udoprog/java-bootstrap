package se.tedro.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.CoffeePress;
import se.tedro.bootstrap.api.Grinder;
import se.tedro.bootstrap.api.Tap;
import se.tedro.bootstrap.cupboard.CheapCoffeePress;
import se.tedro.bootstrap.cupboard.CheapGrinder;
import se.tedro.bootstrap.cupboard.CoffeePressBrewer;
import se.tedro.bootstrap.cupboard.PublicWaterTap;
import se.tedro.bootstrap.cupboard.ThermoPotBoiler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CoffeeMakerGuice {
    public static void main(String[] argv) {
        final Module m = new AbstractModule() {
            @Provides
            @Singleton
            public ExecutorService executor() {
                return Executors.newFixedThreadPool(4);
            }

            @Provides
            @Singleton
            public ScheduledExecutorService scheduler() {
                return Executors.newScheduledThreadPool(4);
            }

            @Override
            protected void configure() {
                bind(Tap.class).to(PublicWaterTap.class);
                bind(Boiler.class).to(ThermoPotBoiler.class);
                bind(CoffeePress.class).to(CheapCoffeePress.class);
                bind(Grinder.class).to(CheapGrinder.class);
            }
        };

        final Injector injector = Guice.createInjector(m);

        CoffeeMaker.run(() -> injector.getInstance(CoffeePressBrewer.class));

        injector.getInstance(ExecutorService.class).shutdown();
        injector.getInstance(ScheduledExecutorService.class).shutdown();

        System.exit(0);
    }
}
