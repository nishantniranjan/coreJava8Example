import java.util.function.Predicate;

public interface Calculation<T> {
    T execute(T[] args, Predicate predicate);
}
