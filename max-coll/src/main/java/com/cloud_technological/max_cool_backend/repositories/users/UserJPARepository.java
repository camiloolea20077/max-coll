package com.cloud_technological.max_cool_backend.repositories.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud_technological.max_cool_backend.entity.UsersEntity;



public interface UserJPARepository extends JpaRepository<UsersEntity, Long>  {
    public Optional<UsersEntity> findByEmail(String email);
}
