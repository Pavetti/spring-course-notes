package pl.pavetti.securityCourse.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String accessToken;
    private String refreshToken;
}
