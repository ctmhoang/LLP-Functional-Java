package Problem3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance {

    private static final Pattern WORD_EXTRACTOR = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String,Long>> VALUE_ORDER = Map.Entry.comparingByValue();
    private static final Comparator<Map.Entry<String,Long>> REVERSED_ORDER = VALUE_ORDER.reversed();


    public static void main(String[] args) {
        var books = List.of("resources/1342.txt","dontExisted.txt");

       books.stream()
               .map(Path::of)
               .map(Either.wrap(Files::lines))
               .peek(p -> p.handle(System.err::println))
               .filter(Either::successful)
               .flatMap(Either::get)
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
