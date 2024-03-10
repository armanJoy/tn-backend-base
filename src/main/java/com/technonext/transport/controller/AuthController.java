package com.technonext.transport.controller;

import com.technonext.transport.common.ApiConstants;
import com.technonext.transport.config.authentication.service.AuthService;
import com.technonext.transport.config.authentication.service.RefreshTokenService;
import com.technonext.transport.dto.request.LoginRequest;
import com.technonext.transport.dto.request.RefreshTokenRequest;
import com.technonext.transport.dto.response.JwtResponse;
import com.technonext.transport.dto.response.RefreshTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = ApiConstants.AUTHENTICATION)
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(ApiConstants.LOGIN)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.authenticateUser(loginRequest), HttpStatus.OK);
    }


    @PostMapping(ApiConstants.REFRESH_TOKEN)
    public ResponseEntity<RefreshTokenResponse> tokenRefresh(@Valid @RequestBody RefreshTokenRequest request) {
        return new ResponseEntity<>(refreshTokenService.tokenRefresh(request), HttpStatus.OK);
    }



}
