package pl.pavetti.securityCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.pavetti.securityCourse.model.Role;
import pl.pavetti.securityCourse.model.UserEntity;
import pl.pavetti.securityCourse.repository.UserRepo;

import java.util.Optional;

@SpringBootApplication
public class SecurityCourseApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SecurityCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<UserEntity> adminAccount = userRepo.findByEmail("admin@admin.pl");
		if(adminAccount.isEmpty()){
			UserEntity userEntity = UserEntity.builder()
					.email("admin@admin.pl")
					.name("adminek")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.role(Role.ADMIN)
					.build();
			userRepo.save(userEntity);

		}
	}
}
