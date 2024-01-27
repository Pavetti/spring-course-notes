package pl.pavetti.securityCourse.security.jwt;

import lombok.Getter;

@Getter
public class JwtConstants {
    public static final String SECRET_KEY = "7982e75695735348f0f4842c295ce9d80d6c0537855245f9ff05d282458e2473";
    public static final Long EXPIRATION_TIME = 43200000L;   // 12 hours in millisecond (1s = 1000ms)
}
