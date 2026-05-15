package bank.repository;

public interface TextMapper<T> {
    String toLine(T item);

    T fromLine(String line);
}
