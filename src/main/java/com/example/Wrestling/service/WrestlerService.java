package com.example.Wrestling.service;

import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.dto.WrestlerResponseDTO;
import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.mapper.PromotionMapper;
import com.example.Wrestling.mapper.WrestlerMapper;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WrestlerService {
    private final WrestlerRepository wrestlerRepository;
    private final PromotionRepository promotionRepository;

    public List<WrestlerDTO> getAllWrestlers() {
        return wrestlerRepository.findAll().stream().map(WrestlerMapper::ToDTO).toList();
    }

    public List<WrestlerDTO> getAllWrestlersBySearch(String search) {
        return wrestlerRepository.findBySearch(search).stream().map(WrestlerMapper::ToDTO).toList();
    }

    public WrestlerResponseDTO getWrestlerById(long id) {
        WrestlerDTO wrestlerDTO = wrestlerRepository.getById(id).orElse(null);
        if (wrestlerDTO == null) return null;
        PromotionDTO promotionDTO = PromotionMapper.ToDTO(Objects.requireNonNull(promotionRepository.findById(wrestlerDTO.getPromotionId()).orElse(null)));
        return new WrestlerResponseDTO(promotionDTO, wrestlerDTO);
    }

    public List<WrestlerDTO> getAllWrestlersByPromotion(long promotionId) {
        return wrestlerRepository.getAllByPromotionId(promotionId).stream().map(WrestlerMapper::ToDTO).toList();
    }

    public List<WrestlerDTO> getAllWrestlersTitleID(long titleId) {
        return wrestlerRepository.getAllByTitleId(titleId).stream().map(WrestlerMapper::ToDTO).toList();
    }

    public WrestlerDTO createWrestler(WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = WrestlerMapper.ToEntity(wrestlerDTO, promotionRepository.findById(wrestlerDTO.getPromotionId()).orElse(null));
        return WrestlerMapper.ToDTO(wrestlerRepository.save(wrestler));
    }

    public WrestlerDTO updateWrestler(long id, WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = WrestlerMapper.ToEntity(wrestlerDTO, promotionRepository.findById(wrestlerDTO.getPromotionId()).orElse(null));
        wrestler.setId(id);
        if(wrestlerRepository.update(wrestler) != 0) return WrestlerMapper.ToDTO(wrestler);
        return WrestlerMapper.ToDTO(new Wrestler());
    }

    public void deleteWrestler(long id) {
        wrestlerRepository.deleteById(id);
    }
}
