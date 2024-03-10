package com.technonext.transport.config.authentication.service;

import com.technonext.transport.common.constant.ApplicationConstant;
import com.technonext.transport.config.authentication.jwt.JwtHelper;
import com.technonext.transport.config.authentication.model.RefreshToken;
import com.technonext.transport.dto.request.LoginRequest;
import com.technonext.transport.dto.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;


    private final RefreshTokenService refreshTokenService;

    private final JwtHelper jwtHelper;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService,
                           JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwtToken = jwtHelper.generateToken(userDetails.getUsername());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

//        if (CollectionUtils.isEmpty(roles)) {
//            throw new CommonServerException();
//        }

        return JwtResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getRefreshToken())
                .roles(roles)
                .type(ApplicationConstant.AUTHORIZATION_TYPE_BEARER)
                .build();
    }
}
