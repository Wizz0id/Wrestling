package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.MatchDTO;
import com.example.Wrestling.entity.Event;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.entity.Wrestler;

import java.util.List;

public class MatchMapper {

    public static MatchDTO ToDTO(Match match){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setName(match.getName());
        matchDTO.setType(match.getType());
        matchDTO.setUrl(match.getUrl());
        matchDTO.setProfessionalRating(match.getProfessionalRating());
        matchDTO.setEventId(match.getEvent().getId());
        matchDTO.setWinnerId(match.getWinnerId());
        matchDTO.setWrestlersId(match.getWrestlers().stream().map(Wrestler::getId).toList());
        return matchDTO;
    }

    public static Match ToEntity(MatchDTO matchDTO, Event event, List<Wrestler> wrestlers){
        Match match = new Match();
        match.setId(matchDTO.getId());
        match.setName(matchDTO.getName());
        match.setType(matchDTO.getType());
        match.setUrl(matchDTO.getUrl());
        match.setProfessionalRating(matchDTO.getProfessionalRating());
        match.setEvent(event);
        match.setWinnerId(matchDTO.getWinnerId());
        match.setWrestlers(wrestlers);
        for (Wrestler wrestler : wrestlers) {
            wrestler.getMatches().add(match);
        }
        return match;
    }
}
