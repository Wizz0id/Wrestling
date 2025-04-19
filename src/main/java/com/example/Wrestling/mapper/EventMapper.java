package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.EventDTO;
import com.example.Wrestling.entity.Event;
import com.example.Wrestling.entity.Promotion;

import java.util.Base64;

public class EventMapper {
    public static EventDTO ToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setPromotionName(event.getPromotion().getName());
        eventDTO.setPromotionPicture(Base64.getEncoder().encodeToString(event.getPromotion().getPicture()));
        return eventDTO;
    }
    public static Event ToEntity(EventDTO dto, Promotion promotion) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setPromotion(promotion);
        return event;
    }
}
