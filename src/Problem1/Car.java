package Problem1;

import java.util.*;
import java.util.function.Predicate;

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

    public Car addGas(int g) {
        return new Car(gasLevel + g, color, passengers, trunkContents);
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

    public static Predicate getRedCarCriterion() {
        return RED_CAR_PREDICATE;
    }

    private static final Predicate<Car> RED_CAR_PREDICATE =
            c -> c.color.equalsIgnoreCase("red");


    public static Predicate<Car> getGasLevelCriterion(int threshold) {
        return c -> c.gasLevel >= threshold;
    }

    public static Predicate<Car> getColorCriterion(String... colors) {
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        return c -> colorSet.contains(c.getColor());
    }

    private static final Comparator<Car> PASSENGER_COUNT_ORDER = Comparator.comparingInt(c -> c.getPassengers().size());

    public static Comparator<Car> getPassengerCountOrder() {
        return PASSENGER_COUNT_ORDER;
    }

}
