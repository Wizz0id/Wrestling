package com.example.Wrestling.service;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.entity.Title;
import com.example.Wrestling.mapper.TitleMapper;
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
        return titleRepository.getAll();
    }

    public List<TitleDTO> getAllBySearch(String search) {
        return titleRepository.findBySearch(search);
    }
    public List<TitleDTO> getAllByWrestler(long wrestlerId) {
        return titleRepository.getAllByWrestler(wrestlerId).stream().map(TitleMapper::ToDTO).toList();
    }
    public TitleDTO getTitleById(long id) {
        return TitleMapper.ToDTO(Objects.requireNonNull(titleRepository.findById(id).orElse(null)));
    }
    public TitleDTO saveTitle(TitleDTO titleDTO) {
        Title title = TitleMapper.ToEntity(titleDTO, promotionRepository.findByName(titleDTO.getPromotionName()).orElse(null),
                wrestlerRepository.findAllById(titleDTO.getWrestlersId()));
        return TitleMapper.ToDTO(titleRepository.save(title));
    }
    public TitleDTO updateTitle(long id, TitleDTO titleDTO) {
        Title title = TitleMapper.ToEntity(titleDTO, promotionRepository.findByName(titleDTO.getPromotionName()).orElse(null),
                wrestlerRepository.findAllById(titleDTO.getWrestlersId()));
        title.setId(id);
        return TitleMapper.ToDTO(titleRepository.save(title));
    }
    public void deleteTitle(long id) {
        titleRepository.deleteById(id);
    }
}
