package Problem1;

@FunctionalInterface
public interface Criterion<E> {
    boolean test(E c);
}
