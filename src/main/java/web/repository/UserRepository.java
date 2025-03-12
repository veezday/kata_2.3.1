package web.repository;

import web.model.User;

import java.util.Optional;

public interface UserRepository {

    void save(User entity);

    void update(User entity);

    void saveAll(Iterable<User> entities);

    Optional<User> findById(Long id);

    Iterable<User> findAll();

    void deleteById(Long id);
}
