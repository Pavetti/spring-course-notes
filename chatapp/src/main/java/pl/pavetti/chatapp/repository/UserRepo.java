package pl.pavetti.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.chatapp.model.Status;
import pl.pavetti.chatapp.model.UserEntity;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,String> {
    List<UserEntity> findAllByStatus(Status status);
}
