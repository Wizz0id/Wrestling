package com.example.Wrestling.service;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.entity.Title;
import com.example.Wrestling.mapper.TitleMapper;
import com.example.Wrestling.repository.PromotionRepository;
import com.example.Wrestling.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class TitleService {
    private final TitleRepository titleRepository;
    private final PromotionRepository promotionRepository;

    public List<TitleDTO> getAllTitles() {
        return titleRepository.findAll().stream().map(TitleMapper::ToDTO).toList();
    }

    public List<TitleDTO> getAllBySearch(String search) {
        return titleRepository.findBySearch(search).stream().map(TitleMapper::ToDTO).toList();
    }
    public TitleDTO getTitleById(long id) {
        return TitleMapper.ToDTO(Objects.requireNonNull(titleRepository.findById(id).orElse(null))); // TODO А оно мне надо?
    }
    public TitleDTO saveTitle(TitleDTO titleDTO) {
        Title title = TitleMapper.ToEntity(titleDTO, promotionRepository.findByName(titleDTO.getPromotionName()).orElse(null));
        return TitleMapper.ToDTO(titleRepository.save(title));
    }
    public TitleDTO updateTitle(long id,TitleDTO titleDTO) {
        Title title = TitleMapper.ToEntity(titleDTO, promotionRepository.findByName(titleDTO.getPromotionName()).orElse(null));
        title.setId(id);
        return TitleMapper.ToDTO(titleRepository.save(title));
    }
    public void deleteTitle(long id) {
        titleRepository.deleteById(id);
    }
}
