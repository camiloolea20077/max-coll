package com.cloud_technological.max_cool_backend.services;

import org.springframework.data.domain.PageImpl;

import com.cloud_technological.max_cool_backend.dto.users.CreateUserDto;
import com.cloud_technological.max_cool_backend.dto.users.UserDto;
import com.cloud_technological.max_cool_backend.dto.users.UsersTableDto;
import com.cloud_technological.max_cool_backend.utils.PageableDto;



public interface UserService {
    UserDto create(CreateUserDto createUserDto);
    // PageImpl<UsersTableDto> pageUsers(PageableDto<Object> pageableDto);
    
}
