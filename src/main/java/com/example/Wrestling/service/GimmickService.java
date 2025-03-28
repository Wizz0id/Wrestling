package com.example.Wrestling.service;

import com.example.Wrestling.dto.GimmickDTO;
import com.example.Wrestling.entity.Gimmick;
import com.example.Wrestling.repository.GimmickRepository;
import com.example.Wrestling.repository.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class GimmickService {
    private final GimmickRepository gimmickRepository;
    private final WrestlerRepository wrestlerRepository;

    public List<GimmickDTO> getAllGimmicksForWrestler(Long wrestlerId) {
        return gimmickRepository.findByWrestlerId(wrestlerId).stream().map(this::ToDTO).toList();
    }
    public List<GimmickDTO> getAllGimmicksBySearch(Long wrestlerId, String search) {
        return gimmickRepository.findByName(wrestlerId, search).stream().map(this::ToDTO).toList();
    }

    public GimmickDTO getGimmick(Long wrestlerId, Long gimmickId) {
        return ToDTO(Objects.requireNonNull(gimmickRepository.findByWrestlerIdAndId(wrestlerId, gimmickId).orElse(null)));
    }

    public GimmickDTO createRenew(GimmickDTO gimmickDTO) {
        Gimmick gimmick = ToGimmick(gimmickDTO);
        return ToDTO(gimmickRepository.save(gimmick));
    }

    public GimmickDTO updateRenew(long id, GimmickDTO gimmickDTO) {
        Gimmick gimmick = ToGimmick(gimmickDTO);
        gimmick.setId(id);
        return ToDTO(gimmickRepository.save(gimmick));
    }

    public void deleteRenew(long id) {
        gimmickRepository.deleteById(id);
    }

    private Gimmick ToGimmick(GimmickDTO gimmickDTO) {
        Gimmick gimmick = new Gimmick();
        gimmick.setId(gimmickDTO.getId());
        gimmick.setName(gimmickDTO.getName());
        gimmick.setWrestler(wrestlerRepository.findByWrestlerId(gimmickDTO.getWrestlerId()).orElse(null)); // TODO попытаться оптимизировать
        return gimmick;
    }
    private GimmickDTO ToDTO(Gimmick gimmick) {
        GimmickDTO gimmickDTO = new GimmickDTO();
        gimmickDTO.setId(gimmick.getId());
        gimmickDTO.setName(gimmick.getName());
        gimmickDTO.setWrestlerId(gimmick.getWrestler().getId());
        return gimmickDTO;
    }
}
