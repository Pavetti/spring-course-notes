package pl.pavetti.securityCourse.config;

import lombok.Getter;

@Getter
public class JwtConstants {
    public static final String SECRET_KEY = "7982e75695735348f0f4842c295ce9d80d6c0537855245f9ff05d282458e2473";
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 30;   // 30 days in millisecond (1s = 1000ms)
}
