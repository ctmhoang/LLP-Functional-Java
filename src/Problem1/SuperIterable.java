package Problem1;

import java.util.Arrays;
import java.util.Iterator;
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

    public SuperIterable<E> filter(Predicate<E> pred){
        return new SuperIterable<>(StreamSupport.stream(self.spliterator(), false).filter(pred).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList(
                "LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen"));

        strings.forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        strings.filter(s -> Character.isUpperCase(s.charAt(0))).forEach(System.out::println);

    }
}
