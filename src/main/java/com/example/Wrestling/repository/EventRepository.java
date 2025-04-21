package com.example.Wrestling.repository;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "select new com.example.Wrestling.dto.EventDTO(event.id, event.name, event.date, promotion.name, promotion.picture) " +
            "from Event event " +
            "left join Promotion promotion on event.promotion.id = promotion.id " +
            "where event.name like %:search% or promotion.name like %:search%")
    List<EventDTO> getBySearch(String search);
    @Query(value = "select new com.example.Wrestling.dto.EventDTO(event.id, event.name, event.date, promotion.name, promotion.picture) " +
            "from Event event " +
            "left join Promotion promotion on event.promotion.id = promotion.id")
    List<EventDTO> getAll();
}
