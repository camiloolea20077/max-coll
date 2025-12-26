package com.cloud_technological.max_cool_backend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    public String email;
    public String password;
    private Long headquartersId;
}
