package com.example.Wrestling.repository;

import com.example.Wrestling.entity.EventRenew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRenewRepository extends JpaRepository<EventRenew, Long> {
    @Query(value = "select * from event_renew where event_id=:eventId",nativeQuery = true)
    List<EventRenew> findAllByEventId(Long eventId);
    @Query(value = "select * from event_renew where event_id=:eventId and id=:id", nativeQuery = true)
    EventRenew findEventRenewByIdAndEventId(Long eventId,Long id);
}
