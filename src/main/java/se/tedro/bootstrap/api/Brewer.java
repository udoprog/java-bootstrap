package se.tedro.bootstrap.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Brewer {
    /**
     * Brew cups of coffee.
     *
     * @param cups The number of cups to brew.
     * @return The brewed coffee cups.
     */
    CompletableFuture<List<Coffee>> brew(int cups);
}
