package com.cappuccino.foodcourter.security;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/public/authenticate";

    // Signing key for HS512 algorithm
    public static final String JWT_SECRET = "kXp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPd";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

}
