package com.technonext.transport.config.authentication.service;

import com.technonext.transport.dto.request.LoginRequest;
import com.technonext.transport.dto.response.JwtResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
