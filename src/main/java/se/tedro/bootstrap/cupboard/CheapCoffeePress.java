package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.CoffeeBeans;
import se.tedro.bootstrap.api.Coffee;
import se.tedro.bootstrap.api.CoffeePress;
import se.tedro.bootstrap.api.Water;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;

public class CheapCoffeePress implements CoffeePress {
    private final ExecutorService executor;

    @Inject
    public CheapCoffeePress(final ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public CompletionStage<List<Coffee>> brew(
        final Water water, final CoffeeBeans beans
    ) {
        return CompletableFuture.supplyAsync(() -> {
            final List<Coffee> coffee = new ArrayList<>();
            final int cups = Math.min(water.getAmount(), beans.getAmount());

            for (int i = 0; i < cups; i++) {
                coffee.add(new PlainCoffee());
            }

            return coffee;
        }, executor);
    }
}
