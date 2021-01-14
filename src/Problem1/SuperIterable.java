package Problem1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SuperIterable<E> implements Iterable<E> {

    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        return new SuperIterable<>(StreamSupport.stream(self.spliterator(), false).filter(pred).collect(Collectors.toList()));
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        return new SuperIterable<F>(StreamSupport.stream(self.spliterator(), false).map(op).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList(
                "LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen"));

        strings.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        strings.filter(s -> Character.isUpperCase(s.charAt(0))).map(x -> x.toUpperCase(Locale.ROOT)).forEach(System.out::println);

        var cars = new SuperIterable<>(Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo"))
        );

        cars.filter(Car.getGasLevelCriterion(6)).map(car -> car.getPassengers().get(0) + " is driving car " + car.getColor() + " with lots of fuel").forEach(System.out::println);

    }
}
