package Problem2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

final class Average {
    private double total;
    private long count;

    public void include(double d) {
        total += d;
        count++;
    }

    public void merge(Average other) {
        total += other.total;
        count += other.count;
    }

    public double get() {
        return total / count;
    }
}

public class CollectAverage {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        var averager = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .parallel()
                .unordered() // maintaining order in parallel mode is really expensive
                .limit(4_000_000_000L).collect(Average::new,Average::include,Average::merge);
        long endTime = System.nanoTime();
        System.out.println(averager.get() + " done in " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
