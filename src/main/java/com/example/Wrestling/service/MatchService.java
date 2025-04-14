package com.example.Wrestling.service;

import com.example.Wrestling.dto.*;
import com.example.Wrestling.entity.Match;
import com.example.Wrestling.mapper.MatchMapper;
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
    private final WrestlerRepository wrestlerRepository;


    public List<MatchDTO> getAll(){
        return matchRepository.findAll().stream().map(MatchMapper::ToDTO).toList();
    }
    public List<MatchDTO> getBySearch(String search){
        return matchRepository.findBySearch(search).stream().map(MatchMapper::ToDTO).toList();
    }
    public MatchDTO getMatchById(long id){
        return MatchMapper.ToDTO(Objects.requireNonNull(matchRepository.getMatchById(id).orElse(null)));
    }

    public List<MatchDTO> getMatchesByWrestlerId(long wrestlerId){
        return matchRepository.getAllMatchesByWrestlerId(wrestlerId).stream().map(MatchMapper::ToDTO).toList();
    }
    public List<MatchDTO> getMatchesByEventId(long eventId){
        return matchRepository.getAllMatchesByEventId(eventId).stream().map(MatchMapper::ToDTO).toList();
    }

    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = MatchMapper.ToEntity(matchDTO, eventRepository.findById(matchDTO.getEventId()).orElse(null),
                wrestlerRepository.findAllById(matchDTO.getWrestlersId()));
        return MatchMapper.ToDTO(matchRepository.save(match));
    }

    public MatchDTO updateMatch(long id, MatchDTO matchDTO) {
        Match match = MatchMapper.ToEntity(matchDTO, eventRepository.findById(matchDTO.getEventId()).orElse(null),
                wrestlerRepository.findAllById(matchDTO.getWrestlersId()));
        match.setId(id);
        return MatchMapper.ToDTO(matchRepository.save(match));
    }

    public void deleteMatch(long id) {
        matchRepository.deleteParticipantsById(id);
        matchRepository.deleteMatchById(id);
    }


}
