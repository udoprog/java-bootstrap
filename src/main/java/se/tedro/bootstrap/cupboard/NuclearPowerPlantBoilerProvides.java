package se.tedro.bootstrap.cupboard;

import dagger.Component;
import se.tedro.bootstrap.LifeCycle;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.EarlyDependencies;

@Component(modules = NuclearPowerPlantBoilerModule.class, dependencies = EarlyDependencies.class)
@Nuclear
interface NuclearPowerPlantBoilerProvides extends Boiler.BoilerProvides {
    @Override
    NuclearPowerPlantBoiler boiler();

    @Override
    LifeCycle life();
}
