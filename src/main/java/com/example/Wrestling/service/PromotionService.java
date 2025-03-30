package com.example.Wrestling.service;

import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.entity.*;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public PromotionDTO getPromotionById(long id) {
        return ToDTO(Objects.requireNonNull(promotionRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public List<PromotionDTO> getPromotionByName(String name) {
        return promotionRepository.findByNameContaining(name).stream().map(this::ToDTO).toList();
    }
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = ToEntity(promotionDTO);
        return ToDTO(promotionRepository.save(promotion));
    }

    public PromotionDTO updatePromotion(long id, PromotionDTO promotionDTO) {
        Promotion promotion = ToEntity(promotionDTO);
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
        promotionDTO.setPicture(Base64.getEncoder().encodeToString(promotion.getPicture()));
        return promotionDTO;

    }
    private Promotion ToEntity(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionDTO.getId());
        promotion.setName(promotionDTO.getName());
        promotion.setFioOfCeo(promotionDTO.getFioOfCeo());
        promotion.setPicture(Base64.getDecoder().decode(promotionDTO.getPicture()));
        return promotion;
    }
}
