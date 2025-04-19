package com.example.Wrestling.repository;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "select event.id, event.name, date, promotion.name, picture " +
            "from event " +
            "left join promotion on event.promo_id = promotion.id " +
            "where event.name ilike '%' || :search || '%' or promotion.name ilike '%' || :search || '%'", nativeQuery = true)
    List<EventDTO> getBySearch(String search);
    @Query(value = "select event.id, event.name, date, promotion.name, picture" +
            " from event " +
            "left join promotion on event.promo_id = promotion.id", nativeQuery = true)
    List<EventDTO> getAll();
}
