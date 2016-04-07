package se.tedro.bootstrap.dagger;

import dagger.Component;
import se.tedro.bootstrap.LifeCycle;
import se.tedro.bootstrap.cupboard.CoffeePressBrewer;

@Component(modules = MainModule.class,
    dependencies = EarlyComponent.class)
@Main
public interface MainComponent {
    CoffeePressBrewer brewer();

    LifeCycle life();
}