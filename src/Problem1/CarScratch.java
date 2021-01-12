package Problem1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CarScratch {

    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp) {
        return c -> comp.compare(target, c);
    }

    public static <E> Predicate<E> isGreater(ToIntFunction<E> target){
        return e -> target.applyAsInt(e) < 0;
    }

    public static <E> void showAll(List<E> lc) {
        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Predicate crit) {
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

//        showAll(getByCriterion(cars, Car.getRedCarCriterion()));
//
//        showAll(getByCriterion(cars, Car.getGasLevelCriterion(6)));
//
//        cars.sort(Car.getPassengerCountOrder());
//        showAll(cars);

//        showAll(getByCriterion(cars, Car.getColorCriterion("Red", "Black")));

//        Predicate<Car> level7 = Car.getGasLevelCriterion(7);
//        showAll(getByCriterion(cars, level7));
//
//        Predicate<Car> notLevel7 = Predicate.not(level7);
//        showAll(getByCriterion(cars, notLevel7));
//
//
//        Predicate<Car> isRed = Car.getColorCriterion("Red");
//        Predicate<Car> fourPassengers = c -> c.getPassengers().size() == 4;
//        Predicate<Car> redFourPassengers = isRed.and(fourPassengers);
//        showAll(getByCriterion(cars, redFourPassengers));
//
//        Predicate<Car> isBlack = Car.getColorCriterion("Black");
//        Predicate<Car> blackOrFourPassenger = isBlack.or(fourPassengers);
//        showAll(getByCriterion(cars, blackOrFourPassenger));

        Car bert = Car.withGasColorPassengers(5, "Blue","Cameron","Eric","David");

        ToIntFunction<Car> compareWithBert = compareWithThis(bert,Car.getPassengerCountOrder());
        cars.forEach(c -> System.out.printf("Comparing %s with bert gives %d\n", c, compareWithBert.applyAsInt(c)));

        showAll(getByCriterion(cars,isGreater(compareWithBert)));

    }
}
