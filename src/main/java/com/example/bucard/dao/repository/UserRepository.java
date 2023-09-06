package com.example.bucard.dao.repository;

import com.example.bucard.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByPhone(String phone);
    Optional<UserEntity> findByPhone(String phone);

    Optional<UserEntity> findByToken(String token);
}
