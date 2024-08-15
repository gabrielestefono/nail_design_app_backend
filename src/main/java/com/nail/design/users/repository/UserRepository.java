package com.nail.design.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nail.design.users.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
}