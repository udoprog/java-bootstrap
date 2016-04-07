package se.tedro.bootstrap.dagger;

import dagger.Module;
import dagger.Provides;
import se.tedro.bootstrap.LifeCycle;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.CoffeePress;
import se.tedro.bootstrap.api.Grinder;
import se.tedro.bootstrap.api.Tap;
import se.tedro.bootstrap.cupboard.CheapCoffeePress;
import se.tedro.bootstrap.cupboard.CheapGrinder;
import se.tedro.bootstrap.cupboard.PublicWaterTap;

import java.util.ArrayList;
import java.util.List;

@Module
public class MainModule {
    private final Boiler.BoilerProvides boiler;

    public MainModule(final Boiler.BoilerProvides boiler) {
        this.boiler = boiler;
    }

    @Provides
    @Main
    Tap tap(PublicWaterTap tap) {
        return tap;
    }

    @Provides
    @Main
    CoffeePress coffeePress(CheapCoffeePress coffeePress) {
        return coffeePress;
    }

    @Provides
    @Main
    Grinder grinder(CheapGrinder grinder) {
        return grinder;
    }

    @Provides
    @Main
    Boiler boiler() {
        return boiler.boiler();
    }

    @Provides
    @Main
    LifeCycle life() {
        final List<LifeCycle> life = new ArrayList<>();
        life.add(boiler.life());
        return LifeCycle.combine(life);
    }
}