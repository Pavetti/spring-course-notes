package pl.pavetti.securityCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pavetti.securityCourse.dto.JwtAuthResponse;
import pl.pavetti.securityCourse.dto.LoginRequest;
import pl.pavetti.securityCourse.dto.RefreshTokenRequest;
import pl.pavetti.securityCourse.dto.RegisterRequest;
import pl.pavetti.securityCourse.model.UserEntity;
import pl.pavetti.securityCourse.service.imlp.AuthenticationService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthenticationService authenticationService;
    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("register")
    public ResponseEntity<UserEntity> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refresh(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
