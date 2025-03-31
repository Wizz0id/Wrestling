package com.example.Wrestling.controller;

import com.example.Wrestling.dto.MatchRenewDTO;
import com.example.Wrestling.service.MatchRenewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches/api/v1/{matchId}/renews")
public class MatchRenewController {
    @Autowired
    private MatchRenewService matchRenewService;
    @GetMapping()
    public ResponseEntity<List<MatchRenewDTO>> getAllMatchRenews(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchRenewService.getAllMatchRenews(matchId));
    }
    @GetMapping("/{renewId}")
    public ResponseEntity<MatchRenewDTO> getMatchRenew(@PathVariable Long matchId, @PathVariable Long renewId) {
        return ResponseEntity.ok(matchRenewService.getMatchRenew(matchId, renewId));
    }
    @PostMapping
    public ResponseEntity<MatchRenewDTO> createMatchRenew(@PathVariable Long matchId, @RequestBody MatchRenewDTO matchRenewDTO) {
        return ResponseEntity.ok(matchRenewService.createRenew(matchId, matchRenewDTO));
    }
    @PutMapping("/{renewId}")
    public ResponseEntity<MatchRenewDTO> updateMatchRenew(@PathVariable Long matchId, @PathVariable Long renewId, @RequestBody MatchRenewDTO renewDTO) {
        return ResponseEntity.ok(matchRenewService.updateRenew(matchId, renewId, renewDTO));
    }
    @DeleteMapping("/{renewId}")
    public ResponseEntity<String> deleteMatchRenew(@PathVariable Long matchId, @PathVariable Long renewId) {
        matchRenewService.deleteRenew(renewId);
        return ResponseEntity.ok("Match renew deleted");
    }
}
