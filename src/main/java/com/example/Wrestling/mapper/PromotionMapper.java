package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.entity.Promotion;

import java.util.Base64;

public class PromotionMapper {

    public static PromotionDTO ToDTO(Promotion promotion) {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setName(promotion.getName());
        promotionDTO.setFioOfCeo(promotion.getFioOfCeo());
        promotionDTO.setPicture(Base64.getEncoder().encodeToString(promotion.getPicture()));
        return promotionDTO;

    }
    public static Promotion ToEntity(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionDTO.getId());
        promotion.setName(promotionDTO.getName());
        promotion.setFioOfCeo(promotionDTO.getFioOfCeo());
        promotion.setPicture(Base64.getDecoder().decode(promotionDTO.getPicture()));
        return promotion;
    }
}
