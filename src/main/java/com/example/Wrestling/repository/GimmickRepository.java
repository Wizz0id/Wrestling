package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Gimmick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GimmickRepository extends JpaRepository<Gimmick, Long> {
    @Query(value = "select * from gimmick where wrestler_id=:wrestlerId and name ilike '%' || :gimmickName || '%'", nativeQuery = true)
    Optional<Gimmick> findByName(Long wrestlerId, String gimmickName);
    List<Gimmick> findByWrestlerId(Long wrestlerId);
    Optional<Gimmick> findByWrestlerIdAndId(Long wrestlerId, Long id);
}
