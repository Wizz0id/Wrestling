package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "select * from event where name ilike '%' || :search || '%'", nativeQuery = true)
    List<Event> getBySearch(String search);
}
