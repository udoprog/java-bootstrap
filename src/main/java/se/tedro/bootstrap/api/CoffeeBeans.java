package se.tedro.bootstrap.api;

/**
 * Represents some coffee beans.
 */
public class CoffeeBeans {
    private final int amount;

    public CoffeeBeans(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
