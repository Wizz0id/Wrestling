package com.example.Wrestling.service;

import com.example.Wrestling.dto.MatchDTO;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor()
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final EventRepository eventRepository;


    public List<MatchDTO> getAll(){
        return matchRepository.findAll().stream().map(this::ToDTO).toList();
    }
    public List<MatchDTO> getBySearch(String search){
        return matchRepository.findBySearch(search).stream().map(this::ToDTO).toList();
    }
    public MatchDTO getMatchById(long id){
        return ToDTO(Objects.requireNonNull(matchRepository.findById(id).orElse(null)));    // TODO А оно мне надо?
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = ToEntity(matchDTO);
        return ToDTO(matchRepository.save(match));
    }

    public MatchDTO updateMatch(long id, MatchDTO renewDTO) {
        Match match = ToEntity(renewDTO);
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
        return matchDTO;
    }

    private Match ToEntity(MatchDTO matchDTO){
        Match match = new Match();
        match.setId(matchDTO.getId());
        match.setName(matchDTO.getName());
        match.setType(matchDTO.getType());
        match.setUrl(matchDTO.getUrl());
        match.setProfessionalRating(matchDTO.getProfessionalRating());
        match.setEvent(eventRepository.findById(matchDTO.getEventId()).orElse(null)); // TODO Оптимизировать бы эххх даааа
        match.setWinnerId(matchDTO.getWinnerId());
        return match;
    }
}
