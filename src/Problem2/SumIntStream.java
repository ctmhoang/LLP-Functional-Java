package Problem2;

import java.util.stream.IntStream;

public class SumIntStream {
    public static void main(String[] args) {
        System.out.println(IntStream.iterate(0, i -> i < 100, i -> i +1).reduce(0,Integer::sum));
    }
}
