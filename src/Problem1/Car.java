package Problem1;

import java.util.Comparator;
import java.util.List;

public class Car {

    private final int gasLevel;
    private final String color;

    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersWithTrunkContent(int gas, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gas, color, List.of(passengers), List.of("jack", "wrench", "spare wheel"));
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Problem1.Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                "," + (trunkContents == null ? "no trunk" : "trunkContents=" + trunkContents) +
                '}';
    }

    public static Criterion getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    private static final Criterion<Car> RED_CAR_CRITERION =
            c -> c.getColor().equalsIgnoreCase("red");


    public static Criterion<Car> getGasLevelCriterion(int threshold) {
        return c -> c.getGasLevel() >= threshold;
    }

    private static final Comparator<Car> PASSENGER_COUNT_ORDER = Comparator.comparingInt(c -> c.getPassengers().size());

    public static Comparator<Car> getPassengerCountOrder() {
        return PASSENGER_COUNT_ORDER;
    }
}
