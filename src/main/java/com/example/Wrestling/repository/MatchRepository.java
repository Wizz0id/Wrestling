package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Match;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query(value = "select * from match where name " +
            "ilike '%' || :search || '%'",nativeQuery = true)
    List<Match> findBySearch(String search);
    Optional<Match> getMatchById(Long matchId);
    @Modifying
    @Transactional
    @Query(value = "delete from participants where match_id=:matchId",nativeQuery = true)
    void deleteParticipantsById(Long matchId);
    @Modifying
    @Transactional
    @Query(value = "delete from match where id=:matchId",nativeQuery = true)
    void deleteMatchById(Long matchId);
}
