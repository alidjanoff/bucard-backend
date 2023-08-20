package com.example.bucard.dao.repository;

import com.example.bucard.dao.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByIdAndUser_Id(Long id, Long id1);
}
