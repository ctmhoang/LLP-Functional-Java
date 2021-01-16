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
        var averager = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI)).limit(1_000).collect(Average::new,Average::include,Average::merge);
        System.out.println(averager.get());
    }
}
