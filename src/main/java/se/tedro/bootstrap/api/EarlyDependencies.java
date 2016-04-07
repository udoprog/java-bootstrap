package se.tedro.bootstrap.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.tedro.bootstrap.LifeCycleRegistry;

import javax.inject.Named;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Globally available dependencies for the entire application.
 */
public interface EarlyDependencies {
    ExecutorService executor();

    ScheduledExecutorService scheduler();

    @Named("config")
    ObjectMapper config();

    LifeCycleRegistry registry();
}
