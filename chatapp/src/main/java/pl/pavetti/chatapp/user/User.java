package pl.pavetti.chatapp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
