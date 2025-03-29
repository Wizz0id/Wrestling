package com.example.Wrestling.controller;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/api/v1")
public class EventController {
    @Autowired
    private EventService eventService;
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(@RequestParam(required = false) String search) {
        if (search == null) return ResponseEntity.ok(eventService.getAllEvents());
        return ResponseEntity.ok(eventService.getAllBySearch(search));
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable long eventId, @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
    }
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted");
    }
}
