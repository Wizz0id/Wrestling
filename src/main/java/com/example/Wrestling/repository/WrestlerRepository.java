package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.enumurate.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    Optional<Wrestler> findByWrestlerId(Long id);
    @Query(value = "select * from wrestler join gimmick as g on wrestler.id = g.wrestler_id" +
            " where fio ilike '%' || :search || '%' or g.name ilike '%' || :search || '%'", nativeQuery = true)
    List<Wrestler> findBySearch(String search);
    List<Wrestler> findByPromotionId(Long promotionId);
    List<Wrestler> findByGender(Gender gender);
    List<Wrestler> findByMatchesId(Long matchesId);
}
