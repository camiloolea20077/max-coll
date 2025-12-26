package com.cloud_technological.max_cool_backend.services;

import com.cloud_technological.max_cool_backend.dto.auth.AuthDto;
import com.cloud_technological.max_cool_backend.dto.auth.LoginDto;

public interface AuthService {
    public AuthDto login(LoginDto loginDto);
    // AuthDto register(RegisterRequestDto dto);

}
