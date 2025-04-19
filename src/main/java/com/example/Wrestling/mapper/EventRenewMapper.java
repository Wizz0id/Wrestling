package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.EventRenewDTO;
import com.example.Wrestling.entity.Event;
import com.example.Wrestling.entity.EventRenew;

public class EventRenewMapper {
    public static EventRenew ToEntity(EventRenewDTO renewDTO, Event event){
        EventRenew eventRenew = new EventRenew();
        eventRenew.setId(renewDTO.getId());
        eventRenew.setRating(renewDTO.getRating());
        eventRenew.setRenew(renewDTO.getRenew());
        eventRenew.setEvent(event);
        return eventRenew;
    }

    public static EventRenewDTO ToDTO(EventRenew renew){
        EventRenewDTO eventRenewDTO = new EventRenewDTO();
        eventRenewDTO.setId(renew.getId());
        eventRenewDTO.setRating(renew.getRating());
        eventRenewDTO.setRenew(renew.getRenew());
        eventRenewDTO.setEventId(renew.getEvent().getId());
        return eventRenewDTO;
    }
}
