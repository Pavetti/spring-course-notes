package pl.pavetti.securityCourse.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pavetti.securityCourse.model.AuthenticationRequest;
import pl.pavetti.securityCourse.model.AuthenticationResponse;
import pl.pavetti.securityCourse.model.RegisterRequerst;
import pl.pavetti.securityCourse.security.jwt.JwtService;
import pl.pavetti.securityCourse.user.Role;
import pl.pavetti.securityCourse.user.UserEntity;
import pl.pavetti.securityCourse.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authManager;
    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public AuthenticationResponse register(RegisterRequerst registerRequerst) {
        var user = UserEntity.builder()
                .firstname(registerRequerst.getFirstname())
                .lastname(registerRequerst.getLastname())
                .email(registerRequerst.getEmail())
                .password(passwordEncoder.encode(registerRequerst.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
