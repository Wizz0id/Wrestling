package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.GimmickDTO;
import com.example.Wrestling.entity.Gimmick;
import com.example.Wrestling.entity.Wrestler;

public class GimmickMapper {
    public static Gimmick ToEntity(GimmickDTO gimmickDTO, Wrestler wrestler) {
        Gimmick gimmick = new Gimmick();
        gimmick.setId(gimmickDTO.getId());
        gimmick.setName(gimmickDTO.getName());
        gimmick.setWrestler(wrestler); // TODO попытаться оптимизировать
        return gimmick;
    }
    public static GimmickDTO ToDTO(Gimmick gimmick) {
        GimmickDTO gimmickDTO = new GimmickDTO();
        gimmickDTO.setId(gimmick.getId());
        gimmickDTO.setName(gimmick.getName());
        gimmickDTO.setWrestlerId(gimmick.getWrestler().getId());
        return gimmickDTO;
    }
}
