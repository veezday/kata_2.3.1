package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<User, Long> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.update(entity);
    }

    @Override
    public void saveAll(List<User> entities) {
        userRepository.saveAll(entities);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
