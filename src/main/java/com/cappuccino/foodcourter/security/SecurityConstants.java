package com.cappuccino.foodcourter.security;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // Signing key for HS512 algorithm
    public static final String JWT_SECRET = "5f46868ca2e74b4cabb69c1c14fb0ba5";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

}
