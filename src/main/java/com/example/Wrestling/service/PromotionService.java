package com.example.Wrestling.service;

import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.entity.*;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.Wrestling.mapper.PromotionMapper;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public List<PromotionDTO> getAllPromotions() {
        return promotionRepository.findAll().stream().map(PromotionMapper::ToDTO).toList();
    }
    public PromotionDTO getPromotionById(long id) {
        return PromotionMapper.ToDTO(Objects.requireNonNull(promotionRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public List<PromotionDTO> getPromotionByName(String name) {
        return promotionRepository.findByNameContaining(name).stream().map(PromotionMapper::ToDTO).toList();
    }
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = PromotionMapper.ToEntity(promotionDTO);
        return PromotionMapper.ToDTO(promotionRepository.save(promotion));
    }

    public PromotionDTO updatePromotion(long id, PromotionDTO promotionDTO) {
        Promotion promotion = PromotionMapper.ToEntity(promotionDTO);
        promotion.setId(id);
        return PromotionMapper.ToDTO(promotionRepository.save(promotion));
    }

    public void deletePromotion(long id) {
        promotionRepository.deleteById(id);
    }

}
