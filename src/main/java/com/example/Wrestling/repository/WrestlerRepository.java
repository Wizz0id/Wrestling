package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    @Query(value = "select wrestler.id, fio, gender, height, picture, retired, start_of_career, trainer, weight, promo_id " +
            "from wrestler left join gimmick on wrestler.id = gimmick.wrestler_id " +
            "where wrestler.fio ilike '%' || :search || '%' or gimmick.name ilike '%' || :search || '%'", nativeQuery = true)
    List<Wrestler> findBySearch(String search);
}
