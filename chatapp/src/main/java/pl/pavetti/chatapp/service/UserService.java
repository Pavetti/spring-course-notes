package pl.pavetti.chatapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pavetti.chatapp.model.Status;
import pl.pavetti.chatapp.model.UserEntity;
import pl.pavetti.chatapp.repository.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void saveUser(UserEntity user){
        user.setStatus(Status.ONLINE);
        userRepo.save(user);
    }

    public void disconntectUser(UserEntity user){
        var storedUser = userRepo.findById(user.getId())
                .orElse(null);
        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepo.save(user);
        }
    }

    public List<UserEntity> findConnectedUsers(){
        return userRepo.findAllByStatus(Status.ONLINE);
    }
}
