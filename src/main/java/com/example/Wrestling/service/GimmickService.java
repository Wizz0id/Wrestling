package com.example.Wrestling.service;

import com.example.Wrestling.dto.GimmickDTO;
import com.example.Wrestling.entity.Gimmick;
import com.example.Wrestling.mapper.GimmickMapper;
import com.example.Wrestling.repository.GimmickRepository;
import com.example.Wrestling.repository.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GimmickService {
    private final GimmickRepository gimmickRepository;
    private final WrestlerRepository wrestlerRepository;

    public List<GimmickDTO> getAllGimmicksForWrestler(Long wrestlerId) {
        List<Gimmick> gimmicks = gimmickRepository.findByWrestlerId(wrestlerId);
        return gimmicks.stream().map(GimmickMapper::ToDTO).toList();
    }

    public GimmickDTO getGimmick(Long wrestlerId, Long gimmickId) {
        return GimmickMapper.ToDTO(Objects.requireNonNull(gimmickRepository.findByWrestlerIdAndId(wrestlerId, gimmickId).orElse(null)));
    }

    public GimmickDTO createRenew(GimmickDTO gimmickDTO) {
        Gimmick gimmick = GimmickMapper.ToEntity(gimmickDTO, wrestlerRepository.findById(gimmickDTO.getWrestlerId()).orElse(null));
        return GimmickMapper.ToDTO(gimmickRepository.save(gimmick));
    }

    public GimmickDTO updateRenew(long id, GimmickDTO gimmickDTO) {
        Gimmick gimmick = GimmickMapper.ToEntity(gimmickDTO, wrestlerRepository.findById(gimmickDTO.getWrestlerId()).orElse(null));
        gimmick.setId(id);
        return GimmickMapper.ToDTO(gimmickRepository.save(gimmick));
    }

    public void deleteRenew(long id) {
        gimmickRepository.deleteById(id);
    }
}
