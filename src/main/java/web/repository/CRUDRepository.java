package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRUDRepository<T> extends JpaRepository<T, Long> {
}
