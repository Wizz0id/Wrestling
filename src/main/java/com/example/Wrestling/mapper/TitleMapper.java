package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.entity.Promotion;
import com.example.Wrestling.entity.Title;

import java.util.Base64;

public class TitleMapper {
    public static TitleDTO ToDTO(Title title) {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setId(title.getId());
        titleDTO.setName(title.getName());
        titleDTO.setStartDate(title.getStart_date());
        titleDTO.setEndDate(title.getEnd_date());
        titleDTO.setPicture(Base64.getEncoder().encodeToString(title.getPicture()));
        titleDTO.setPromotionName(title.getPromotion().getName());
        titleDTO.setPromotionPicture(Base64.getEncoder().encodeToString(title.getPromotion().getPicture()));
        return titleDTO;
    }
    public static Title ToEntity(TitleDTO titleDTO, Promotion promotion) {
        Title title = new Title();
        title.setId(titleDTO.getId());
        title.setName(titleDTO.getName());
        title.setStart_date(titleDTO.getStartDate());
        title.setEnd_date(titleDTO.getEndDate());
        title.setPicture(Base64.getDecoder().decode(titleDTO.getPicture()));
        title.setPromotion(promotion);
        return title;
    }
}
