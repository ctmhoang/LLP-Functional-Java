package Problem3;

import java.util.function.Consumer;
import java.util.function.Function;

public class Either <E> {
    private E data;
    private Throwable problem;

    private Either(){};

    public static <E> Either<E> success(E v){
        var self = new Either<E>();
        self.data = v;
        return self;
    }

    public static <E> Either<E> failure(Throwable t){
        var self = new Either<E>();
        self.problem = t;
        return self;
    }

    public boolean successful() {
        return data != null;
    }

    public boolean failed(){
        return problem != null;
    }

    public E get(){
        return data;
    }

    public Throwable getProblem(){
        return problem;
    }

    public void use(Consumer<E> op){
        if(successful()) op.accept(data);
    }

    public void handle(Consumer<Throwable> op){
        if(failed()) op.accept(problem);
    }

    public static <E,F> Function<E,Either<F>> wrap(ExceptionFunction<E,F> op){
        return e -> {
          try {
              return Either.success(op.apply(e));
          } catch (Throwable throwable) {
              return Either.failure(throwable);
          }
        };
    }
}
