package bank.repository;

import bank.domain.Identifiable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {
    List<T> findAll();

    Optional<T> findById(String id);

    void save(T item);

    void saveAll(Collection<T> items);

    void deleteById(String id);
}
