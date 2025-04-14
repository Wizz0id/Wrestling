package com.example.Wrestling.controller;

import com.example.Wrestling.dto.MatchDTO;
import com.example.Wrestling.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches/api/v1")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @GetMapping
    public ResponseEntity<List<MatchDTO>> getMatches(@RequestParam(required = false) String search) {
        if (search == null) return ResponseEntity.ok(matchService.getAll());
        return ResponseEntity.ok(matchService.getBySearch(search));
    }
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable int matchId) {
        MatchDTO matchDTO = matchService.getMatchById(matchId);
        return ResponseEntity.ok(matchDTO);
    }
    @GetMapping(value = "/matches", params = "wrestler")
    public ResponseEntity<List<MatchDTO>> getMatchesByWrestler(@RequestParam(name = "wrestler") long wrestlerId){
        return ResponseEntity.ok(matchService.getMatchesByWrestlerId(wrestlerId));
    }
    @GetMapping(value = "/matches", params = "event")
    public ResponseEntity<List<MatchDTO>> getMatchesByEvent(@RequestParam(name = "event") long eventId){
        return ResponseEntity.ok(matchService.getMatchesByEventId(eventId));
    }
    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        return ResponseEntity.ok(matchService.createMatch(matchDTO));
    }
    @PutMapping("/{matchId}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable int matchId, @RequestBody MatchDTO matchDTO) {
        return ResponseEntity.ok(matchService.updateMatch(matchId, matchDTO));
    }
    @DeleteMapping("/{matchId}")
    public ResponseEntity<String> deleteMatch(@PathVariable int matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok("Match deleted");
    }
}
