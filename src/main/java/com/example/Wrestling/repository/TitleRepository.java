package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    List<Title> findByWrestlerId(Long id);
    List<Title> findByPromotionId(Long promotionId);
}
