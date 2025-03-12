package web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(User entity) {
        em.persist(entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        em.merge(entity);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<User> entities) {
        for (User entity : entities) {
            save(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.remove(em.find(User.class, id));
    }
}
