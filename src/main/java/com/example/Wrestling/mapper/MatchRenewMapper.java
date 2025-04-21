package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.MatchRenewDTO;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.entity.MatchRenew;

public class MatchRenewMapper {

    public static MatchRenew ToRenew(MatchRenewDTO renewDTO, Match match){
        MatchRenew matchRenew = new MatchRenew();
        matchRenew.setId(renewDTO.getId());
        matchRenew.setUsername(renewDTO.getUsername());
        matchRenew.setRating(renewDTO.getRating());
        matchRenew.setRenew(renewDTO.getRenew());
        matchRenew.setMatch(match);
        return matchRenew;
    }

    public static MatchRenewDTO ToDTO(MatchRenew renew){
        MatchRenewDTO matchRenewDTO = new MatchRenewDTO();
        matchRenewDTO.setId(renew.getId());
        matchRenewDTO.setUsername(renew.getUsername());
        matchRenewDTO.setRating(renew.getRating());
        matchRenewDTO.setRenew(renew.getRenew());
        return matchRenewDTO;
    }
}
