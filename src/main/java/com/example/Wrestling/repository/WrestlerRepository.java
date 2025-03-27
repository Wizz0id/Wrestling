package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.enumurate.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    Optional<Wrestler> findById(Long id);
    Optional<Wrestler> findByFio(String fio);
    List<Wrestler> findByPromotionId(Long promotionId);
    List<Wrestler> findByGender(Gender gender);
    List<Wrestler> findByMatchesId(Long matchesId);
}
