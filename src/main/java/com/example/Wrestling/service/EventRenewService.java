package com.example.Wrestling.service;

import com.example.Wrestling.dto.EventRenewDTO;
import com.example.Wrestling.entity.EventRenew;
import com.example.Wrestling.repository.EventRenewRepository;
import com.example.Wrestling.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor()
@Service
public class EventRenewService {
    private final EventRenewRepository eventRenewRepository;
    private final EventRepository eventRepository;

    public List<EventRenewDTO> getAllEventRenews(long eventId) {
        return eventRenewRepository.findAllByEventId(eventId).stream().map(this::ToDTO).toList();
    }

    public EventRenewDTO getEventRenew(long eventId, long eventRenewId) {
        return ToDTO(eventRenewRepository.findEventRenewByIdAndEventId(eventId, eventRenewId));
    }

    public EventRenewDTO createRenew(EventRenewDTO renewDTO) {
        EventRenew renew = ToEntity(renewDTO);
        return ToDTO(eventRenewRepository.save(renew));
    }

    public EventRenewDTO updateRenew(long id, EventRenewDTO renewDTO) {
        EventRenew event = ToEntity(renewDTO);
        event.setId(id);
        return ToDTO(eventRenewRepository.save(event));
    }

    public void deleteRenew(long id) {
        eventRenewRepository.deleteById(id);
    }

    private EventRenew ToEntity(EventRenewDTO renewDTO){
        EventRenew eventRenew = new EventRenew();
        eventRenew.setId(renewDTO.getId());
        eventRenew.setRating(renewDTO.getRating());
        eventRenew.setRenew(renewDTO.getRenew());
        eventRenew.setEvent(eventRepository.findById(renewDTO.getEventId()).orElse(null)); // TODO попытаться оптимизировать
        return eventRenew;
    }

    private EventRenewDTO ToDTO(EventRenew renew){
        EventRenewDTO eventRenewDTO = new EventRenewDTO();
        eventRenewDTO.setId(renew.getId());
        eventRenewDTO.setRating(renew.getRating());
        eventRenewDTO.setRenew(renew.getRenew());
        eventRenewDTO.setEventId(renew.getEvent().getId());
        return eventRenewDTO;
    }
}
