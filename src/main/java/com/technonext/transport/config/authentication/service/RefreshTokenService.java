package com.technonext.transport.config.authentication.service;

import com.technonext.transport.config.authentication.model.RefreshToken;
import com.technonext.transport.dto.request.RefreshTokenRequest;
import com.technonext.transport.dto.response.RefreshTokenResponse;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyRefreshTokenExpiration(RefreshToken refreshToken);

    RefreshTokenResponse tokenRefresh(RefreshTokenRequest request);
}
