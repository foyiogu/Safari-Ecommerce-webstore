package com.decagon.safariwebstore.utils;

public class SecurityAuthorisationConstants {

    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PUBLIC_URIS = new String[]{
            "/",
            // -- Swagger UI v3 (OpenAPI) Start
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/login",
            "/register",
            "/admin/register"
            // -- Swagger UI v3 (OpenAPI) End

    };
}
