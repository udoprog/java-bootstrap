package se.tedro.bootstrap.api;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface CoffeePress {
    CompletionStage<List<Coffee>> brew(Water water, CoffeeBeans beans);
}
