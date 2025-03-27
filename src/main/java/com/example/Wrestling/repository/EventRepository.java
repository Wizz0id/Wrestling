package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByPromotionId(Long id);
    List<Event> findByName(String name);
    List<Event> findByDate(LocalDate date);
}
