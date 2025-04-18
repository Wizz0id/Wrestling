package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Gimmick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GimmickRepository extends JpaRepository<Gimmick, Long> {
    @Query(value = "select * from gimmick where wrestler_id=:wrestlerId", nativeQuery = true)
    List<Gimmick> findByWrestlerId(Long wrestlerId);
    @Query(value = "select gimmick.id, gimmick.name, gimmick.wrestler_id " +
            "from gimmick left join wrestler " +
            "on gimmick.wrestler_id=wrestler.id " +
            "where wrestler_id=:wrestlerId and gimmick.id=:id", nativeQuery = true)
    Optional<Gimmick> findByWrestlerIdAndId(Long wrestlerId, Long id);
}
