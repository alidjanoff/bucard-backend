package com.example.bucard.dao.repository;

import com.example.bucard.dao.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoxRepository extends JpaRepository<BoxEntity, Long> {
    boolean existsByIdAndProfiles_Id(Long id, Long id1);

    boolean existsByTitleAndUser_Id(String title, Long id);

    BoxEntity findByProfiles_IdAndUser_Id(Long id, Long id1);

    

    


    
}
