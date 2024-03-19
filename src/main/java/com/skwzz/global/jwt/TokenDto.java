package com.skwzz.global.jwt;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDto {

    private String accessToken;
    private String refreshToken;
}
