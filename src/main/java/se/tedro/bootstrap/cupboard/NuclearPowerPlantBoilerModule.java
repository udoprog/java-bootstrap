package se.tedro.bootstrap.cupboard;

import dagger.Module;
import dagger.Provides;
import se.tedro.bootstrap.LifeCycle;

@Module
public class NuclearPowerPlantBoilerModule {
    @Provides
    @Nuclear
    LifeCycle life(NuclearPowerPlantBoiler boiler) {
        return registry -> {
            registry.start(boiler::start);
        };
    }
}
