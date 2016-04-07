package se.tedro.bootstrap.dagger;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.common.collect.ImmutableList;
import dagger.Module;
import dagger.Provides;
import se.tedro.bootstrap.LifeCycleRegistry;
import se.tedro.bootstrap.api.Boiler;
import se.tedro.bootstrap.cupboard.NuclearPowerPlantBoiler;
import se.tedro.bootstrap.cupboard.ThermoPotBoiler;

import javax.inject.Named;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

@Module
public class EarlyModule {
    final Queue<Supplier<CompletableFuture<Void>>> startup = new ConcurrentLinkedQueue<>();
    final Queue<Supplier<CompletableFuture<Void>>> shutdown = new ConcurrentLinkedQueue<>();

    @Provides
    @Early
    ExecutorService executor() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @Early
    ScheduledExecutorService scheduler() {
        return Executors.newScheduledThreadPool(4);
    }

    @Provides
    @Early
    @Named("config")
    ObjectMapper config() {
        final ObjectMapper m = new ObjectMapper(new YAMLFactory());

        // Optional support
        m.registerModule(new Jdk8Module());

        // use the 'type' field to determine type of boiler config.
        m.addMixIn(Boiler.Config.class, TypeNameMixin.class);

        // register modules.
        m.registerSubtypes(new NamedType(ThermoPotBoiler.Config.class, "thermo"));
        m.registerSubtypes(new NamedType(NuclearPowerPlantBoiler.Config.class, "nuclear"));

        return m;
    }

    @Provides
    @Early
    LifeCycleRegistry registry() {
        return new LifeCycleRegistry() {
            @Override
            public void start(
                final Supplier<CompletableFuture<Void>> hook
            ) {
                startup.add(hook);
            }

            @Override
            public void stop(
                final Supplier<CompletableFuture<Void>> hook
            ) {
                shutdown.add(hook);
            }
        };
    }

    @Provides
    @Early
    @Named("startup")
    List<Supplier<CompletableFuture<Void>>> startup() {
        return ImmutableList.copyOf(startup);
    }

    @Provides
    @Early
    @Named("shutdown")
    List<Supplier<CompletableFuture<Void>>> shutdown() {
        return ImmutableList.copyOf(shutdown);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
        property = "type")
    interface TypeNameMixin {
    }
}