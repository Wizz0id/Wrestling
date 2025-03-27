package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Optional<Title> findByName(String name);
    List<Title> findByWrestlerId(Long id);
    List<Title> findByPromotionId(Long promotionId);
}
