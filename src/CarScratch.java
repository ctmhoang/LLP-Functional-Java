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

    public static List<Car> getColoredCar(Iterable<Car> in, String color) {
        return StreamSupport.stream(in.spliterator(), false).filter(car -> car.getColor().equalsIgnoreCase(color)).collect(Collectors.toUnmodifiableList());
    }

    public static void main(String[] args) {
        var cars = List.of(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
        showAll(cars);
        showAll(getColoredCar(cars, "Black"));

    }
}
