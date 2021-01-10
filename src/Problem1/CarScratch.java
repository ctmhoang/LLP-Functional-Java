package Problem1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CarScratch {

    public static <E> void showAll(List<E> lc) {
        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Criterion crit) {
        return StreamSupport.stream(in.spliterator(), false).filter(crit::test).collect(Collectors.toUnmodifiableList());
    }


    public static void main(String[] args) {
        var cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
        showAll(cars);

        showAll(getByCriterion(cars, Car.getRedCarCriterion()));

        showAll(getByCriterion(cars, Car.getGasLevelCriterion(6)));

        cars.sort(Car.getPassengerCountOrder());
        showAll(cars);

    }
}
