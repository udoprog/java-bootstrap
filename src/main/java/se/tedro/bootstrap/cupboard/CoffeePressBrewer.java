package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.CoffeeBeans;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.Brewer;
import se.tedro.bootstrap.api.Coffee;
import se.tedro.bootstrap.api.CoffeePress;
import se.tedro.bootstrap.api.Grinder;
import se.tedro.bootstrap.api.Tap;
import se.tedro.bootstrap.api.Water;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CoffeePressBrewer implements Brewer {
    private final Tap tap;
    private final Boiler boiler;
    private final CoffeePress coffeePress;
    private final Grinder grinder;

    @Inject
    public CoffeePressBrewer(
        final Tap tap, final Boiler boiler, final CoffeePress coffeePress, final Grinder grinder
    ) {
        this.tap = tap;
        this.boiler = boiler;
        this.coffeePress = coffeePress;
        this.grinder = grinder;
    }

    public CompletableFuture<List<Coffee>> brew(final int cups) {
        final CompletableFuture<Water> boiledWater = tap.tap(cups).thenCompose(boiler::boil);
        final CompletableFuture<CoffeeBeans> groundBeans = grinder.grind(cups);

        return boiledWater
            .thenCombine(groundBeans, WaterAndBeans::new)
            .thenCompose(waterAndBeans -> coffeePress.brew(waterAndBeans.getWater(),
                waterAndBeans.getBeans()));
    }

    static class WaterAndBeans {
        private final Water water;
        private final CoffeeBeans beans;

        public WaterAndBeans(final Water water, final CoffeeBeans beans) {
            this.water = water;
            this.beans = beans;
        }

        public Water getWater() {
            return water;
        }

        public CoffeeBeans getBeans() {
            return beans;
        }
    }
}
