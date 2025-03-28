package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByName(String name);
    @Query(value = "select * from promotion where name ilike '%' || :name || '%'", nativeQuery = true)
    List<Promotion> findByNameContaining(String name);
}
