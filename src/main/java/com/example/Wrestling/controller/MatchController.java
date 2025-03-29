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
        return ResponseEntity.ok(matchService.getMatchById(matchId));
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
