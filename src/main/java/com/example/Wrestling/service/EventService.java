package com.example.Wrestling.service;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.entity.Event;
import com.example.Wrestling.mapper.EventMapper;
import com.example.Wrestling.repository.EventRepository;
import com.example.Wrestling.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final PromotionRepository promotionRepository;

    public List<EventDTO> getAllEvents() {
        return eventRepository.getAll();
    }
    public List<EventDTO> getAllBySearch(String search) {
        return eventRepository.getBySearch(search);
    }
    public EventDTO getEventById(long id) {
        return EventMapper.ToDTO(Objects.requireNonNull(eventRepository.findById(id).orElse(null)));
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = EventMapper.ToEntity(eventDTO, promotionRepository.findByName(eventDTO.getPromotionName()).orElse(null));
        return EventMapper.ToDTO(eventRepository.save(event));
    }

    public EventDTO updateEvent(long id, EventDTO eventDTO) {
        Event event = EventMapper.ToEntity(eventDTO, promotionRepository.findByName(eventDTO.getPromotionName()).orElse(null));
        event.setId(id);
        return EventMapper.ToDTO(eventRepository.save(event));
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }


}
