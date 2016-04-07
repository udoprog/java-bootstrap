package se.tedro.bootstrap.dagger;

import dagger.Component;
import se.tedro.bootstrap.api.EarlyDependencies;

import javax.inject.Named;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Component(modules = EarlyModule.class)
@Early
public interface EarlyComponent extends EarlyDependencies {
    @Named("startup")
    List<Supplier<CompletableFuture<Void>>> startup();

    @Named("shutdown")
    List<Supplier<CompletableFuture<Void>>> shutdown();
}