package se.tedro.bootstrap.api;

import java.util.concurrent.CompletableFuture;

public interface Tap {
    /**
     * Tap some water.
     *
     * @param amount The amount of water to tap.
     * @return The given amount of water.
     */
    CompletableFuture<Water> tap(final int amount);
}
