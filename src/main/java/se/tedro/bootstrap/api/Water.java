package se.tedro.bootstrap.api;

/**
 * Represents some water.
 */
public class Water {
    private final int temperature;
    private final int amount;

    public Water(final int temperature, final int amount) {
        this.temperature = temperature;
        this.amount = amount;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getAmount() {
        return amount;
    }
}
