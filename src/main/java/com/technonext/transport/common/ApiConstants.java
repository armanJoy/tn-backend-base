package com.technonext.transport.common;

public class ApiConstants {
    public static final String API_PREFIX = "api";

    public static final String VERSION_V1 = "/v1";
    public static final String BASE_URL_V1 = API_PREFIX + VERSION_V1;
    public static final String AUTHENTICATION = "Authentication";
    public static final String TEST = "Test";
    public static final String USER = "User";
    public static final String PATH_VARIABLE_BY_ID = "/{id}";
    public static final String GET_LIST = "/list";
    private static final String AUTH = "/auth";
    public static final String AUTH_URL_V1 = BASE_URL_V1 + AUTH;
    public static final String REFRESH_TOKEN = AUTH_URL_V1 + "/refresh-token";
    public static final String LOGIN = AUTH_URL_V1 + "/login";
    public static final String PING = ApiConstants.BASE_URL_V1 + "/ping";
    public static final String USER_CTRL = BASE_URL_V1 + "/user";
    public static final String CREATE_USER = USER_CTRL;
    public static final String CREATE_ADMIN_USER = USER_CTRL + "/admin";
    public static final String[] PUBLIC_ENDPOINTS = new String[]{
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/**",
            "/api/v1/ping",
            CREATE_USER,
            CREATE_ADMIN_USER
    };
}
