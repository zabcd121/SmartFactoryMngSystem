package com.mirae.smartfactory.config.jwt;

public interface JwtProperties {
    String SECRET_KEY = "k941kas1ktz46";
    long EXPIRATION_TIME = 864000000; // 10일 (1/1000초): 864000000      30 * 60 * 1000L
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_AUTHORIZATION = "Authorization";
}
