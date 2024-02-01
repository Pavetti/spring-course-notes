package pl.pavetti.securityCourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.securityCourse.model.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
