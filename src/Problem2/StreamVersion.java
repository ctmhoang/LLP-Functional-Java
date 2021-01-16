package Problem2;

import Problem1.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StreamVersion {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
                "LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen");

        strings.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        strings.stream().filter(s -> Character.isUpperCase(s.charAt(0))).map(x -> x.toUpperCase(Locale.ROOT)).forEach(System.out::println);

        var cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );

        cars.stream().filter(Car.getGasLevelCriterion(6)).map(car -> car.getPassengers().get(0) + " is driving car " + car.getColor() + " with lots of fuel").forEach(System.out::println);

        System.out.println("-----------------------------------------------------");
        cars.stream().map(car -> car.addGas(3)).forEach(System.out::println);

        System.out.println("-----------------------------------------------------");
        cars.stream().filter(c -> c.getPassengers().size() > 3).flatMap(car -> car.getPassengers().stream()).map(s -> s.toUpperCase(Locale.ROOT)).forEach(System.out::println);

        System.out.println("-----------------------------------------------------");
        cars.stream().flatMap(c -> c.getPassengers().stream().map(s -> s + " is riding in a " + c.getColor() + " car")).forEach(System.out::println);
    }
}
