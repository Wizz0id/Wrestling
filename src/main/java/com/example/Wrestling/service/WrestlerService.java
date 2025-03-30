package com.example.Wrestling.service;

import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class WrestlerService {
    private final WrestlerRepository wrestlerRepository;
    private final PromotionRepository promotionRepository;

    public List<WrestlerDTO> getAllWrestlers() {
        return wrestlerRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public List<WrestlerDTO> getAllWrestlersBySearch(String search) {
        return wrestlerRepository.findBySearch(search).stream().map(this::ToDTO).toList();
    }

    public WrestlerDTO getWrestlerById(long id) {
        return ToDTO(Objects.requireNonNull(wrestlerRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }

    public WrestlerDTO createWrestler(WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = ToEntity(wrestlerDTO);
        return ToDTO(wrestlerRepository.save(wrestler));
    }

    public WrestlerDTO updateWrestler(long id, WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = ToEntity(wrestlerDTO);
        wrestler.setId(id);
        return ToDTO(wrestlerRepository.save(wrestler));
    }

    public void deleteWrestler(long id) {
        wrestlerRepository.deleteById(id);
    }

    private WrestlerDTO ToDTO(Wrestler wrestler) {
        WrestlerDTO wrestlerDTO = new WrestlerDTO();
        wrestlerDTO.setId(wrestler.getId());
        wrestlerDTO.setFio(wrestler.getFio());
        wrestlerDTO.setHeight(wrestler.getHeight());
        wrestlerDTO.setWeight(wrestler.getWeight());
        wrestlerDTO.setPicture(Base64.getEncoder().encodeToString(wrestler.getPicture()));
        wrestlerDTO.setGender(wrestler.getGender());
        wrestlerDTO.setTrainer(wrestler.getTrainer());
        wrestlerDTO.setStartOfCareer(wrestler.getStartOfCareer());
        wrestlerDTO.setRetired(wrestler.isRetired());
        wrestlerDTO.setPromotionPicture(Base64.getEncoder().encodeToString(wrestler.getPromotion().getPicture()));
        wrestlerDTO.setPromotionName(wrestler.getPromotion().getName());
        return wrestlerDTO;
    }
    private Wrestler ToEntity(WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = new Wrestler();
        wrestler.setId(wrestlerDTO.getId());
        wrestler.setFio(wrestlerDTO.getFio());
        wrestler.setHeight(wrestlerDTO.getHeight());
        wrestler.setWeight(wrestlerDTO.getWeight());
        wrestler.setPicture(Base64.getDecoder().decode(wrestlerDTO.getPicture()));
        wrestler.setGender(wrestlerDTO.getGender());
        wrestler.setTrainer(wrestlerDTO.getTrainer());
        wrestler.setStartOfCareer(wrestlerDTO.getStartOfCareer());
        wrestler.setRetired(wrestlerDTO.isRetired());
        wrestler.setPromotion(promotionRepository.findByName(wrestlerDTO.getPromotionName()).orElse(null));
        return wrestler;
    }
}
