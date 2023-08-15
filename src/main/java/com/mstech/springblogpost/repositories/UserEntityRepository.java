package com.mstech.springblogpost.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mstech.springblogpost.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);
}
