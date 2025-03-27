package com.example.Wrestling.service;

import com.example.Wrestling.dto.MatchDTO;
import com.example.Wrestling.dto.MatchRenewDTO;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.entity.MatchRenew;
import com.example.Wrestling.entity.Wrestler;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final EventRepository eventRepository;
    private final MatchRenewRepository matchRenewRepository;
    private final WrestlerRepository wrestlerRepository;


    public List<MatchDTO> getAll(){
        return matchRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public MatchDTO getMatchById(long id){
        return ToDTO(Objects.requireNonNull(matchRepository.findById(id).orElse(null)));    // TODO А оно мне надо?
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = ToMatch(matchDTO);
        return ToDTO(matchRepository.save(match));
    }

    public MatchDTO updateMatch(long id, MatchDTO renewDTO) {
        Match match = ToMatch(renewDTO);
        match.setId(id);
        return ToDTO(matchRepository.save(match));
    }

    public void deleteMatch(long id) {
        matchRepository.deleteById(id);
    }

    private MatchDTO ToDTO(Match match){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setName(match.getName());
        matchDTO.setType(match.getType());
        matchDTO.setUrl(match.getUrl());
        matchDTO.setProfessionalRating(match.getProfessionalRating());
        matchDTO.setEventId(match.getEvent().getId());
        matchDTO.setWinnerId(match.getWinnerId());
        matchDTO.setMatchRenewsIDs(match.getMatchRenews().stream().map(MatchRenew::getId).toList());
        matchDTO.setWrestlersIDs(match.getWrestlers().stream().map(Wrestler::getId).toList());
        return matchDTO;
    }

    private Match ToMatch(MatchDTO matchDTO){
        Match match = new Match();
        match.setId(matchDTO.getId());
        match.setName(matchDTO.getName());
        match.setType(matchDTO.getType());
        match.setUrl(matchDTO.getUrl());
        match.setProfessionalRating(matchDTO.getProfessionalRating());
        match.setEvent(eventRepository.findById(matchDTO.getEventId()).orElse(null)); // TODO Оптимизировать бы эххх даааа
        match.setWinnerId(matchDTO.getWinnerId());
        match.setMatchRenews(matchRenewRepository.findAllById(matchDTO.getMatchRenewsIDs())); // аналогично
        match.setWrestlers(wrestlerRepository.findAllById(matchDTO.getWrestlersIDs())); // аналогично
        return match;
    }
}
