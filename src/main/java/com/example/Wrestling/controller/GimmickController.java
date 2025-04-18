package com.example.Wrestling.controller;

import com.example.Wrestling.dto.GimmickDTO;
import com.example.Wrestling.service.GimmickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wrestlers/api/v1/{wrestlerId}/gimmicks")
public class GimmickController {
    @Autowired
    private GimmickService gimmickService;
    @GetMapping
    public ResponseEntity<List<GimmickDTO>> getAllGimmicks(@PathVariable Long wrestlerId) {
        return ResponseEntity.ok(gimmickService.getAllGimmicksForWrestler(wrestlerId));
    }
    @GetMapping("/{gimmickId}")
    public ResponseEntity<GimmickDTO> getGimmick(@PathVariable Long wrestlerId, @PathVariable Long gimmickId) {
        return ResponseEntity.ok(gimmickService.getGimmick(gimmickId, wrestlerId));
    }
    @PostMapping
    public ResponseEntity<GimmickDTO> createGimmick(@PathVariable Long wrestlerId, @RequestBody GimmickDTO gimmick) {
        gimmick.setWrestlerId(wrestlerId);
        return ResponseEntity.ok(gimmickService.createRenew(gimmick));
    }
    @PutMapping("/{gimmickId}")
    public ResponseEntity<GimmickDTO> updateGimmick(@PathVariable Long wrestlerId, @PathVariable Long gimmickId, @RequestBody GimmickDTO gimmick) {
        gimmick.setWrestlerId(wrestlerId);
        return ResponseEntity.ok(gimmickService.updateRenew(gimmickId, gimmick));
    }
    @DeleteMapping("/{gimmickId}")
    public ResponseEntity<String> deleteGimmick(@PathVariable Long wrestlerId, @PathVariable Long gimmickId) {
        gimmickService.deleteRenew(gimmickId);
        return ResponseEntity.ok("Gimmick deleted");
    }
}
