package com.example.Wrestling.service;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.entity.Event;
import com.example.Wrestling.entity.EventRenew;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.repository.EventRenewRepository;
import com.example.Wrestling.repository.EventRepository;
import com.example.Wrestling.repository.MatchRepository;
import com.example.Wrestling.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final PromotionRepository promotionRepository;
    private final MatchRepository matchRepository;
    private final EventRenewRepository eventRenewRepository;

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::ToDTO).collect(Collectors.toList());
    }
    public EventDTO getEventById(long id) {
        return ToDTO(Objects.requireNonNull(eventRepository.findById(id).orElse(null)));    // TODO А оно мне надо?
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = ToEvent(eventDTO);
        return ToDTO(eventRepository.save(event));
    }

    public EventDTO updateEvent(long id, EventDTO eventDTO) {
        Event event = ToEvent(eventDTO);
        event.setId(id);
        return ToDTO(eventRepository.save(event));
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }

    private EventDTO ToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setPromotion(event.getPromotion().getName());
        eventDTO.setEventRenewIDs(event.getEventRenews().stream().map(EventRenew::getId).collect(Collectors.toList()));
        eventDTO.setMatchIDs(event.getMatches().stream().map(Match::getId).collect(Collectors.toList()));
        return eventDTO;
    }
    private Event ToEvent(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setPromotion(promotionRepository.findByName(dto.getName()).orElse(null)); // TODO Оптимизировать бы эххх даааа
        event.setEventRenews(eventRenewRepository.findAllById(dto.getEventRenewIDs())); // аналогично
        event.setMatches(matchRepository.findAllById(dto.getMatchIDs()));   // аналогично
        return event;
    }
}
