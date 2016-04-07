package se.tedro.bootstrap.cupboard;

import dagger.Component;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.api.EarlyDependencies;

@Component(dependencies = EarlyDependencies.class)
interface ThermoPotBoilerProvides extends Boiler.BoilerProvides {
    @Override
    ThermoPotBoiler boiler();
}