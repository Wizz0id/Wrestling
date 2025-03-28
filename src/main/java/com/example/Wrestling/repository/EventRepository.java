package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByPromotionId(Long id);
    @Query(value = "select * from event where name ilike '%' || :name || '%'",nativeQuery = true)
    List<Event> findByName(String name);
    List<Event> findByDate(LocalDate date);
}
