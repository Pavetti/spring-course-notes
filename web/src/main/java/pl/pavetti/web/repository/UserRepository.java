package pl.pavetti.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.web.security.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
