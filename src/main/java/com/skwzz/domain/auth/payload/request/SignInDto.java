package com.skwzz.domain.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {

    private String username;
    private String password;
}
