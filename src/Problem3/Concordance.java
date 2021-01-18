package Problem3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance {

    private static final Pattern WORD_EXTRACTOR = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String,Long>> VALUE_ORDER = Map.Entry.comparingByValue();
    private static final Comparator<Map.Entry<String,Long>> REVERSED_ORDER = VALUE_ORDER.reversed();


    public static void main(String[] args) throws Throwable {
        Files.lines(Path.of("resources/1342.txt"))
                .flatMap(WORD_EXTRACTOR::splitAsStream)
                .filter(w -> w.length() > 0)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .sorted(REVERSED_ORDER)
                .limit(200)
                .forEach(entry -> System.out.printf("%20s : %5d\n" , entry.getKey(), entry.getValue()));
    }

}
