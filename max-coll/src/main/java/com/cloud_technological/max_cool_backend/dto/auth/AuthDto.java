package com.cloud_technological.max_cool_backend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    private UserDetailDto user;
    private String token;
}
