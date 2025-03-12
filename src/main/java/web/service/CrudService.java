package web.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    void save(T entity);

    void update(T entity);

    void saveAll(List<T> entities);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    void deleteById(ID id);
}
