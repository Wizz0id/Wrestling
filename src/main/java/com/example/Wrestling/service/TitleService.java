package com.example.Wrestling.service;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.entity.Title;
import com.example.Wrestling.repository.PromotionRepository;
import com.example.Wrestling.repository.TitleRepository;
import com.example.Wrestling.repository.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class TitleService {
    private final TitleRepository titleRepository;
    private final PromotionRepository promotionRepository;
    private final WrestlerRepository wrestlerRepository;

    public List<TitleDTO> getAllTitles() {
        return titleRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public TitleDTO getTitleById(long id) {
        return ToDTO(Objects.requireNonNull(titleRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public List<TitleDTO> getAllTitlesByPromotionId(long promotionId) {
        return titleRepository.findByPromotionId(promotionId).stream().map(this::ToDTO).toList();
    }
    public List<TitleDTO> getAllTitlesByWrestlerId(long wrestlerId) {
        return titleRepository.findByWrestlerId(wrestlerId).stream().map(this::ToDTO).toList();
    }
    public TitleDTO saveTitle(TitleDTO titleDTO) {
        Title title = ToTitle(titleDTO);
        return ToDTO(titleRepository.save(title));
    }
    public TitleDTO updateTitle(long id,TitleDTO titleDTO) {
        Title title = ToTitle(titleDTO);
        title.setId(id);
        return ToDTO(titleRepository.save(title));
    }
    public void deleteTitle(long id) {
        titleRepository.deleteById(id);
    }
    private TitleDTO ToDTO(Title title) {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setId(title.getId());
        titleDTO.setName(title.getName());
        titleDTO.setStartDate(title.getStart_date());
        titleDTO.setEndDate(title.getEnd_date());
        titleDTO.setPromotionID(title.getPromotion().getId());
        titleDTO.setWrestlerID(title.getWrestler().getId());
        return titleDTO;
    }
    private Title ToTitle(TitleDTO titleDTO) {
        Title title = new Title();
        title.setId(titleDTO.getId());
        title.setName(titleDTO.getName());
        title.setStart_date(titleDTO.getStartDate());
        title.setEnd_date(titleDTO.getEndDate());
        title.setPromotion(promotionRepository.findById(titleDTO.getPromotionID()).orElse(null));
        title.setWrestler(wrestlerRepository.findByWrestlerId(titleDTO.getWrestlerID()).orElse(null));
        return title;
    }
}
