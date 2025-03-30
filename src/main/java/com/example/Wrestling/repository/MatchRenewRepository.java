package com.example.Wrestling.repository;

import com.example.Wrestling.entity.MatchRenew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MatchRenewRepository extends JpaRepository<MatchRenew, Long> {
    @Query(value = "select * from match_renew where match_id=:matchId", nativeQuery = true)
    List<MatchRenew> findAllByMatchId(Long matchId);
    Optional<MatchRenew> findMatchRenewByMatchIdAndId(Long matchId, Long id);
}
