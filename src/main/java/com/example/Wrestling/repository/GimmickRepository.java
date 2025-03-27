package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Gimmick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GimmickRepository extends JpaRepository<Gimmick, Long> {
    Optional<Gimmick> findByName(String gimmickName);
    List<Gimmick> findByWrestlerId(Long wrestlerId);
}
