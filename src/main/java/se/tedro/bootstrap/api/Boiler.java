package se.tedro.bootstrap.api;

import se.tedro.bootstrap.LifeCycle;

import javax.inject.Named;
import java.util.concurrent.CompletableFuture;

public interface Boiler {
    /**
     * Boil some water.
     * <p>
     * Boiling the water should hopefully increase it's temperature.
     *
     * @param water Water to boil.
     * @return The boiled water.
     */
    CompletableFuture<Water> boil(Water water);

    interface Config {
        BoilerProvides provide(EarlyDependencies dependencies);
    }

    interface BoilerProvides {
        Boiler boiler();

        default LifeCycle life() {
            return LifeCycle.empty();
        }
    }
}
