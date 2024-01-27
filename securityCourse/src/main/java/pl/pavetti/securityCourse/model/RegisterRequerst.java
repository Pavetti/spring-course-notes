package pl.pavetti.securityCourse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequerst {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public boolean validate(){
        return StringUtils.hasText(firstname)
                && StringUtils.hasText(lastname)
                && StringUtils.hasText(email)
                && StringUtils.hasText(password);
    }
}
