package com.example.Wrestling.repository;

import com.example.Wrestling.entity.MatchRenew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRenewRepository extends JpaRepository<MatchRenew, Long> {
}
