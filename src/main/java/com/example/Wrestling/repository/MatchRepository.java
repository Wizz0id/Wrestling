package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Match;
import com.example.Wrestling.enumurate.MatchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByName(String name);
    List<Match> findByEventId(Long eventId);
    List<Match> findByWrestlersId(Long wrestlersId);
    List<Match> findByWinnerId(Long winnerId);
    List<Match> findByType(MatchType type);
}
