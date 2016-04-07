package se.tedro.bootstrap.dagger;

import se.tedro.bootstrap.api.Boiler;

import java.beans.ConstructorProperties;
import java.util.Optional;

public class Config {
    private final Optional<Boiler.Config> boiler;

    @ConstructorProperties({"boiler"})
    public Config(final Optional<Boiler.Config> boiler) {
        this.boiler = boiler;
    }

    public Optional<Boiler.Config> getBoiler() {
        return boiler;
    }
}