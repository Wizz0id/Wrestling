package com.example.Wrestling.service;

import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.entity.Gimmick;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.entity.Title;
import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.enumurate.Gender;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class WrestlerService {
    private final WrestlerRepository wrestlerRepository;
    private final PromotionRepository promotionRepository;
    private final GimmickRepository gimmickRepository;
    private final TitleRepository titleRepository;
    private final MatchRepository matchRepository;

    public List<WrestlerDTO> getAllWrestlers() {
        return wrestlerRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public WrestlerDTO getWrestlerById(long id) {
        return ToDTO(Objects.requireNonNull(wrestlerRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public List<WrestlerDTO> getWrestlerByPromotionId(long promotionId) {
        return wrestlerRepository.findByPromotionId(promotionId).stream().map(this::ToDTO).toList();
    }
    public List<WrestlerDTO> getWrestlerByGender(Gender gender) {
        return wrestlerRepository.findByGender(gender).stream().map(this::ToDTO).toList();
    }
    public List<WrestlerDTO> getWrestlerByMatches(Long id) {
        return wrestlerRepository.findByMatchesId(id).stream().map(this::ToDTO).toList();
    }

    public WrestlerDTO createWrestler(WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = ToWrestler(wrestlerDTO);
        return ToDTO(wrestlerRepository.save(wrestler));
    }

    public WrestlerDTO updateWrestler(long id, WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = ToWrestler(wrestlerDTO);
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
        wrestlerDTO.setPicture(wrestler.getPicture());
        wrestlerDTO.setGender(wrestler.getGender());
        wrestlerDTO.setTrainer(wrestler.getTrainer());
        wrestlerDTO.setStartOfCareer(wrestler.getStartOfCareer());
        wrestlerDTO.setRetired(wrestler.isRetired());
        wrestlerDTO.setPromotionID(wrestler.getPromotion().getId());
        wrestlerDTO.setGimmicksIDs(wrestler.getGimmicks().stream().map(Gimmick::getId).toList());
        wrestlerDTO.setTitlesIDs(wrestler.getTitles().stream().map(Title::getId).toList());
        wrestlerDTO.setMatchesIDs(wrestler.getMatches().stream().map(Match::getId).toList());
        return wrestlerDTO;
    }
    private Wrestler ToWrestler(WrestlerDTO wrestlerDTO) {
        Wrestler wrestler = new Wrestler();
        wrestler.setId(wrestlerDTO.getId());
        wrestler.setFio(wrestlerDTO.getFio());
        wrestler.setHeight(wrestlerDTO.getHeight());
        wrestler.setWeight(wrestlerDTO.getWeight());
        wrestler.setPicture(wrestlerDTO.getPicture());
        wrestler.setGender(wrestlerDTO.getGender());
        wrestler.setTrainer(wrestlerDTO.getTrainer());
        wrestler.setStartOfCareer(wrestlerDTO.getStartOfCareer());
        wrestler.setRetired(wrestlerDTO.isRetired());
        wrestler.setPromotion(promotionRepository.findById(wrestlerDTO.getPromotionID()).orElse(null));
        wrestler.setGimmicks(gimmickRepository.findAllById(wrestlerDTO.getGimmicksIDs()));
        wrestler.setTitles(titleRepository.findAllById(wrestlerDTO.getTitlesIDs()));
        wrestler.setMatches(matchRepository.findAllById(wrestlerDTO.getMatchesIDs()));
        return wrestler;
    }
}
