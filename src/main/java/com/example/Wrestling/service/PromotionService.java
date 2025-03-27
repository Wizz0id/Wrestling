package com.example.Wrestling.service;

import com.example.Wrestling.dto.MatchDTO;
import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.entity.*;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final EventRepository eventRepository;
    private final WrestlerRepository wrestlerRepository;
    private final TitleRepository titleRepository;

    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public PromotionDTO getPromotionById(long id) {
        return ToDTO(Objects.requireNonNull(promotionRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = ToPromotion(promotionDTO);
        return ToDTO(promotionRepository.save(promotion));
    }

    public PromotionDTO updatePromotion(long id, PromotionDTO promotionDTO) {
        Promotion promotion = ToPromotion(promotionDTO);
        promotion.setId(id);
        return ToDTO(promotionRepository.save(promotion));
    }

    public void deletePromotion(long id) {
        promotionRepository.deleteById(id);
    }

    private PromotionDTO ToDTO(Promotion promotion) {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setName(promotion.getName());
        promotionDTO.setFioOfCeo(promotion.getFioOfCeo());
        promotionDTO.setWrestlersIDs(promotion.getWrestlers().stream().map(Wrestler::getId).toList());
        promotionDTO.setEventsIDs(promotion.getEvents().stream().map(Event::getId).toList());
        promotionDTO.setTitlesIDs(promotion.getTitles().stream().map(Title::getId).toList());
        return promotionDTO;

    }
    private Promotion ToPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionDTO.getId());
        promotion.setName(promotionDTO.getName());
        promotion.setFioOfCeo(promotionDTO.getFioOfCeo());
        promotion.setWrestlers(wrestlerRepository.findAllById(promotionDTO.getWrestlersIDs()));
        promotion.setEvents(eventRepository.findAllById(promotionDTO.getEventsIDs()));
        promotion.setTitles(titleRepository.findAllById(promotionDTO.getTitlesIDs()));
        return promotion;
    }
}
