package me.jeonghui.springbootlevel2.service;

import lombok.RequiredArgsConstructor;
import me.jeonghui.springbootlevel2.entity.RefreshToken;
import me.jeonghui.springbootlevel2.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Token"));
    }

}
