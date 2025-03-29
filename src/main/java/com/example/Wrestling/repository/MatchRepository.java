package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query(value = "select * from match where name ilike '%' || :search || '%'",nativeQuery = true)
    List<Match> findBySearch(String search);
}
