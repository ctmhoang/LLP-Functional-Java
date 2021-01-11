package Problem1;

import java.util.Arrays;

@FunctionalInterface
public interface Criterion<E> {
    boolean test(E c);

    static <E> Criterion<E> negate(Criterion<E> crit) {
        return c -> !crit.test(c);
    }

    @SafeVarargs
    static <E> Criterion<E> and(Criterion<E>... crits) {
        return c -> Arrays.stream(crits).allMatch(crit -> crit.test(c));
    }

    @SafeVarargs
    static <E> Criterion<E> or(Criterion<E>... crits) {
        return c -> Arrays.stream(crits).anyMatch(crit -> crit.test(c));
    }

}
