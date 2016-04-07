package se.tedro.bootstrap;

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

public class CoffeeMaker101 {
    public static void main(String[] argv) {
        final ExecutorService executor = Executors.newFixedThreadPool(4);
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

        final Tap tap = new PublicWaterTap();
        final Boiler boiler = new ThermoPotBoiler(scheduler);
        final CoffeePress coffeePress = new CheapCoffeePress(executor);
        final Grinder grinder = new CheapGrinder(scheduler);

        CoffeeMaker.run(() -> new CoffeePressBrewer(tap, boiler, coffeePress, grinder));

        executor.shutdown();
        scheduler.shutdown();

        System.exit(0);
    }
}
