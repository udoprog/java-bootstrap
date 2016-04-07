package se.tedro.bootstrap.cupboard;

import se.tedro.bootstrap.api.Tap;
import se.tedro.bootstrap.api.Water;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class PublicWaterTap implements Tap {
    @Inject
    public PublicWaterTap() {
    }

    @Override
    public CompletableFuture<Water> tap(final int amount) {
        return CompletableFuture.completedFuture(new Water(5, amount));
    }
}
