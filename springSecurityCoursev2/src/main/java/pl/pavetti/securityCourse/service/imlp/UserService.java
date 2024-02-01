package pl.pavetti.securityCourse.service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pavetti.securityCourse.repository.UserRepo;

@Service

public class UserService {

    private UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetailsService userDetailsService(){
        return username -> userRepo.findByEmail(username).orElseThrow( () ->
                new UsernameNotFoundException("cant find user"));
    }
}
