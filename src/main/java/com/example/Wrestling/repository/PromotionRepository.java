package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @Query(value = "select * from promotion where name ilike '%' || :name || '%' or fio_of_ceo ilike '%' || :name || '%'", nativeQuery = true)
    List<Promotion> findByNameContaining(String name);
    Optional<Promotion> findByName(String name);
}
