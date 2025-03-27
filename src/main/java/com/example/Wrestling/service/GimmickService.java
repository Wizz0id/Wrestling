package com.example.Wrestling.service;

import com.example.Wrestling.dto.EventRenewDTO;
import com.example.Wrestling.dto.GimmickDTO;
import com.example.Wrestling.entity.EventRenew;
import com.example.Wrestling.entity.Gimmick;
import com.example.Wrestling.repository.GimmickRepository;
import com.example.Wrestling.repository.WrestlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GimmickService {
    private final GimmickRepository gimmickRepository;
    private final WrestlerRepository wrestlerRepository;

    public List<GimmickDTO> getAllGimmicksForWrestler(Long wrestlerId) {
        return gimmickRepository.findByWrestlerId(wrestlerId).stream().map(this::ToDTO).toList();
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
        gimmick.setWrestler(wrestlerRepository.findById(gimmickDTO.getWrestlerId()).orElse(null)); // TODO попытаться оптимизировать
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
