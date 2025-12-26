package com.cloud_technological.max_cool_backend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDto {
    private Integer id;
    private String name;
    private String email;
}
