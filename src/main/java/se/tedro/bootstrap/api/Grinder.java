package se.tedro.bootstrap.api;

import java.util.concurrent.CompletableFuture;

public interface Grinder {
    CompletableFuture<CoffeeBeans> grind(int cups);
}
