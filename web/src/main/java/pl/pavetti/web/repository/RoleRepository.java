package pl.pavetti.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.web.security.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
