package Problem1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CarScratch {

    public static void showAll(List<Car> lc) {
        for (Car c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }

    public static List<Car> getCarByCriterion(Iterable<Car> in, CarCriterion crit) {
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

        CarCriterion redCarCriterion = c -> c.getColor().equalsIgnoreCase("red");
        showAll(getCarByCriterion(cars, redCarCriterion));

        showAll(getCarByCriterion(cars, new Car.GasLevelCriterion(6)));

        Comparator<Car> passengerCountOrder = Comparator.comparingInt(c -> c.getPassengers().size());
        cars.sort(passengerCountOrder);
        showAll(cars);

    }
}
