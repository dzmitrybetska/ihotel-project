package by.academy.project.hotel.repositories;


import java.util.List;
import java.util.Optional;

public interface CrudRepository<K extends Number, R, V> {

    Optional<V> add(R r);

    List<V> read();

    Optional<V> update(R r);

    Optional<V> delete(K k);
}
