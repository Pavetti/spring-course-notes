package pl.pavetti.securityCourse.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pavetti.securityCourse.model.AuthenticationRequest;
import pl.pavetti.securityCourse.model.AuthenticationResponse;
import pl.pavetti.securityCourse.model.RegisterRequerst;
import pl.pavetti.securityCourse.user.Role;
import pl.pavetti.securityCourse.user.UserEntity;
import pl.pavetti.securityCourse.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("0 ----------------------------------------------------------------------------------------");
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        System.out.println("1 ----------------------------------------------------------------------------------------");
        var jwtToken = jwtService.generateToken(user);
        System.out.println("2 ----------------------------------------------------------------------------------------");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
