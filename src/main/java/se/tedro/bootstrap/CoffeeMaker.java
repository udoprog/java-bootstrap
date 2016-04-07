package se.tedro.bootstrap;

import se.tedro.bootstrap.api.Brewer;
import se.tedro.bootstrap.api.Coffee;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by udoprog on 2016-04-05.
 */
public class CoffeeMaker {
    static void run(final Supplier<Brewer> brewerSupplier) {
        final Brewer brewer = brewerSupplier.get();

        System.out.println("Some coffee would be nice, time to get brewing...");
        final List<Coffee> coffee = brewer.brew(2).join();
        System.out.println("Coffee: " + coffee);
    }
}
