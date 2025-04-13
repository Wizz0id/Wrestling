package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.entity.Promotion;
import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.enumurate.Gender;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

@RequiredArgsConstructor
public class WrestlerMapper {
    public static WrestlerDTO ToDTO(Wrestler wrestler) {
        WrestlerDTO wrestlerDTO = new WrestlerDTO();
        wrestlerDTO.setId(wrestler.getId());
        wrestlerDTO.setFio(wrestler.getFio());
        wrestlerDTO.setHeight(wrestler.getHeight());
        wrestlerDTO.setWeight(wrestler.getWeight());
        wrestlerDTO.setPicture(Base64.getEncoder().encodeToString(wrestler.getPicture()));
        wrestlerDTO.setGender(wrestler.getGender().getDescription());
        wrestlerDTO.setTrainer(wrestler.getTrainer());
        wrestlerDTO.setStartOfCareer(wrestler.getStartOfCareer());
        wrestlerDTO.setRetired(wrestler.isRetired());
        wrestlerDTO.setPromotionId(wrestler.getPromotion().getId());
        return wrestlerDTO;
    }
    public static Wrestler ToEntity(WrestlerDTO wrestlerDTO, Promotion promotion) {
        Wrestler wrestler = new Wrestler();
        wrestler.setId(wrestlerDTO.getId());
        wrestler.setFio(wrestlerDTO.getFio());
        wrestler.setHeight(wrestlerDTO.getHeight());
        wrestler.setWeight(wrestlerDTO.getWeight());
        wrestler.setPicture(Base64.getDecoder().decode(wrestlerDTO.getPicture()));
        wrestler.setGender(Gender.valueOf(wrestlerDTO.getGender()));
        wrestler.setTrainer(wrestlerDTO.getTrainer());
        wrestler.setStartOfCareer(wrestlerDTO.getStartOfCareer());
        wrestler.setRetired(wrestlerDTO.isRetired());
        wrestler.setPromotion(promotion);
        return wrestler;
    }
}
