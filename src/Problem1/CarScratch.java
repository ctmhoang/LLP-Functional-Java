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

    public static <E> Criterion<E> negate(Criterion<E> crit) {
        return c -> !crit.test(c);
    }

    public static <E> Criterion<E> and(Criterion<E>... crits) {
        return c -> Arrays.stream(crits).allMatch(crit -> crit.test(c));
    }

    public static <E> Criterion<E> or(Criterion<E>... crits) {
        return c -> Arrays.stream(crits).anyMatch(crit -> crit.test(c));
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

        Criterion<Car> level7 = Car.getGasLevelCriterion(7);
        showAll(getByCriterion(cars, level7));

        Criterion<Car> notLevel7 = negate(level7);
        showAll(getByCriterion(cars, notLevel7));


        Criterion<Car> isRed = Car.getColorCriterion("Red");
        Criterion<Car> fourPassengers = c -> c.getPassengers().size() == 4;
        Criterion<Car> redFourPassengers = and(isRed,fourPassengers);
        showAll(getByCriterion(cars, redFourPassengers));

        Criterion<Car> isBlack = Car.getColorCriterion("Black");
        Criterion<Car> blackOrFourPassenger = or(isBlack,fourPassengers);
        showAll(getByCriterion(cars, blackOrFourPassenger));


    }
}
