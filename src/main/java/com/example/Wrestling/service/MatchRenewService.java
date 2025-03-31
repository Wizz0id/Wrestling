package com.example.Wrestling.service;

import com.example.Wrestling.dto.MatchRenewDTO;
import com.example.Wrestling.entity.MatchRenew;
import com.example.Wrestling.mapper.MatchRenewMapper;
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
        return matchRenewRepository.findAllByMatchId(matchID).stream().map(MatchRenewMapper::ToDTO).toList();
    }

    public MatchRenewDTO getMatchRenew(long matchID, long renewID) {
        return MatchRenewMapper.ToDTO(matchRenewRepository.findMatchRenewByMatchIdAndId(matchID, renewID).orElse(new MatchRenew()));
    }

    public MatchRenewDTO createRenew(long matchId, MatchRenewDTO renewDTO) {
        MatchRenew renew = MatchRenewMapper.ToRenew(renewDTO, matchRepository.findById(matchId).orElse(null));
        return MatchRenewMapper.ToDTO(matchRenewRepository.save(renew));
    }

    public MatchRenewDTO updateRenew(long matchId,long id, MatchRenewDTO renewDTO) {
        MatchRenew event = MatchRenewMapper.ToRenew(renewDTO, matchRepository.findById(matchId).orElse(null));
        event.setId(id);
        return MatchRenewMapper.ToDTO(matchRenewRepository.save(event));
    }

    public void deleteRenew(long id) {
        matchRenewRepository.deleteById(id);
    }

}
