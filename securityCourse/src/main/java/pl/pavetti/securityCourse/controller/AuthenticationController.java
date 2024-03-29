package pl.pavetti.securityCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pavetti.securityCourse.security.AuthenticationService;
import pl.pavetti.securityCourse.model.AuthenticationRequest;
import pl.pavetti.securityCourse.model.AuthenticationResponse;
import pl.pavetti.securityCourse.model.RegisterRequerst;
import pl.pavetti.securityCourse.user.UserRepository;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

    private AuthenticationService authService;
    private UserRepository userRepository;
    @Autowired
    public AuthenticationController(AuthenticationService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequerst registerRequerst
    ){
        if(!registerRequerst.validate()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerRequerst.getEmail())){

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(authService.register(registerRequerst));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }
}
