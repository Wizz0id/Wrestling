package com.example.Wrestling.service;

import com.example.Wrestling.dto.EventRenewDTO;
import com.example.Wrestling.entity.EventRenew;
import com.example.Wrestling.mapper.EventRenewMapper;
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
        return eventRenewRepository.findAllByEventId(eventId).stream().map(EventRenewMapper::ToDTO).toList();
    }

    public EventRenewDTO getEventRenew(long eventId, long eventRenewId) {
        return EventRenewMapper.ToDTO(eventRenewRepository.findEventRenewByIdAndEventId(eventId, eventRenewId));
    }

    public EventRenewDTO createRenew(EventRenewDTO renewDTO) {
        EventRenew renew = EventRenewMapper.ToEntity(renewDTO, eventRepository.findById(renewDTO.getEventId()).orElse(null));
        return EventRenewMapper.ToDTO(eventRenewRepository.save(renew));
    }

    public EventRenewDTO updateRenew(long id, EventRenewDTO renewDTO) {
        EventRenew event = EventRenewMapper.ToEntity(renewDTO, eventRepository.findById(renewDTO.getEventId()).orElse(null));
        event.setId(id);
        return EventRenewMapper.ToDTO(eventRenewRepository.save(event));
    }

    public void deleteRenew(long id) {
        eventRenewRepository.deleteById(id);
    }

}
