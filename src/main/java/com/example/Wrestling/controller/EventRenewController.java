package com.example.Wrestling.controller;

import com.example.Wrestling.dto.EventRenewDTO;
import com.example.Wrestling.service.EventRenewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/api/v1/{eventId}/renews")
public class EventRenewController {
    @Autowired
    private EventRenewService eventRenewService;
    @GetMapping
    public ResponseEntity<List<EventRenewDTO>> getAllMatchRenews(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventRenewService.getAllEventRenews(eventId));
    }
    @GetMapping("/{renewId}")
    public ResponseEntity<EventRenewDTO> getMatchRenew(@PathVariable Long renewId, @PathVariable Long eventId) {
        return ResponseEntity.ok(eventRenewService.getEventRenew(eventId, renewId));
    }
    @PostMapping
    public ResponseEntity<EventRenewDTO> createEventRenew(@PathVariable Long eventId, @RequestBody EventRenewDTO eventRenewDTO) {
        eventRenewDTO.setEventId(eventId);
        return ResponseEntity.ok(eventRenewService.createRenew(eventRenewDTO));
    }
    @PutMapping("/{renewId}")
    public ResponseEntity<EventRenewDTO> updateEventRenew(@PathVariable Long eventId, @PathVariable Long renewId,@RequestBody EventRenewDTO eventRenewDTO) {
        eventRenewDTO.setEventId(eventId);
        return ResponseEntity.ok(eventRenewService.updateRenew(renewId, eventRenewDTO));
    }
    @DeleteMapping("/{renewId}")
    public ResponseEntity<String> deleteEventRenew(@PathVariable Long eventId, @PathVariable Long renewId) {
        eventRenewService.deleteRenew(renewId);
        return ResponseEntity.ok("Event Renew Deleted");
    }
}
