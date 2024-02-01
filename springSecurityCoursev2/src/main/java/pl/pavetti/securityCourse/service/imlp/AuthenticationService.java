package pl.pavetti.securityCourse.service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pavetti.securityCourse.dto.JwtAuthResponse;
import pl.pavetti.securityCourse.dto.LoginRequest;
import pl.pavetti.securityCourse.dto.RefreshTokenRequest;
import pl.pavetti.securityCourse.dto.RegisterRequest;
import pl.pavetti.securityCourse.model.Role;
import pl.pavetti.securityCourse.model.UserEntity;
import pl.pavetti.securityCourse.repository.UserRepo;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepo userRepo, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserEntity register(RegisterRequest registerRequest){
        UserEntity userEntity = UserEntity.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        return userRepo.save(userEntity);
    }

    public JwtAuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        var user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new IllegalArgumentException("Invalid email")
        );
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(accessToken);
        jwtAuthResponse.setRefreshToken(refreshToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        UserEntity user = userRepo.findByEmail(username).orElseThrow(() ->
                new IllegalArgumentException("Invalid email")
        );
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var accessToken = jwtService.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setAccessToken(accessToken);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponse;
        }
        return null;
    }
}
