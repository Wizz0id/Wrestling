package com.example.Wrestling.service;

import com.example.Wrestling.dto.MatchRenewDTO;
import com.example.Wrestling.entity.MatchRenew;
import com.example.Wrestling.repository.MatchRenewRepository;
import com.example.Wrestling.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor()
@Service
public class MatchRenewService {
    private final MatchRenewRepository matchRenewRepository;
    private final MatchRepository matchRepository;

    public List<MatchRenewDTO> getAllMatchRenews(long matchID) {
        return matchRenewRepository.findAllByMatchId(matchID).stream().map(this::ToDTO).toList();
    }

    public MatchRenewDTO getMatchRenew(long matchID, long renewID) {
        return ToDTO(matchRenewRepository.findMatchRenewByMatchIdAndId(matchID, renewID));
    }

    public MatchRenewDTO createRenew(MatchRenewDTO renewDTO) {
        MatchRenew renew = ToRenew(renewDTO);
        return ToDTO(matchRenewRepository.save(renew));
    }

    public MatchRenewDTO updateRenew(long id, MatchRenewDTO renewDTO) {
        MatchRenew event = ToRenew(renewDTO);
        event.setId(id);
        return ToDTO(matchRenewRepository.save(event));
    }

    public void deleteRenew(long id) {
        matchRenewRepository.deleteById(id);
    }

    private MatchRenew ToRenew(MatchRenewDTO renewDTO){
        MatchRenew matchRenew = new MatchRenew();
        matchRenew.setId(renewDTO.getId());
        matchRenew.setRating(renewDTO.getRating());
        matchRenew.setRenew(renewDTO.getRenew());
        matchRenew.setMatch(matchRepository.findById(renewDTO.getMatchId()).orElse(null)); // TODO попытаться оптимизировать
        return matchRenew;
    }

    private MatchRenewDTO ToDTO(MatchRenew renew){
        MatchRenewDTO matchRenewDTO = new MatchRenewDTO();
        matchRenewDTO.setId(renew.getId());
        matchRenewDTO.setRating(renew.getRating());
        matchRenewDTO.setRenew(renew.getRenew());
        matchRenewDTO.setMatchId(renew.getMatch().getId());
        return matchRenewDTO;
    }
}
