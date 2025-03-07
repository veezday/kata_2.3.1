package web.service;

import java.util.List;

public interface CRUDService<T> {
    void create(T t);
    void update(T t);
    void delete(T t);
    T get(T t);
    List<T> getAll();
}
