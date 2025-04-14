package com.example.Wrestling.controller;

import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.dto.WrestlerResponseDTO;
import com.example.Wrestling.service.WrestlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wrestlers/api/v1")
public class WrestlerController {
    @Autowired
    private WrestlerService wrestlerService;

    @GetMapping
    public ResponseEntity<List<WrestlerDTO>> getAllWrestlers(@RequestParam(required = false) String search) { // Нормальный запрос
        if(search == null) return ResponseEntity.ok(wrestlerService.getAllWrestlers());
        return ResponseEntity.ok(wrestlerService.getAllWrestlersBySearch(search));
    }
    @GetMapping("/{wrestlerId}")
    public ResponseEntity<WrestlerResponseDTO> getWrestlerById(@PathVariable Long wrestlerId) {
        return ResponseEntity.ok(wrestlerService.getWrestlerById(wrestlerId));
    }
    @GetMapping(value = "/wrestlers", params = "promotion")
    public ResponseEntity<List<WrestlerDTO>> getWrestlersByPromotionID(@RequestParam(name = "promotion") Long promotionId) {
        return ResponseEntity.ok(wrestlerService.getAllWrestlersByPromotion(promotionId));
    }
    @GetMapping(value = "/wrestlers", params = "title")
    public ResponseEntity<List<WrestlerDTO>> getWrestlersByTitleID(@RequestParam(name = "title") Long titleId) {
        return ResponseEntity.ok(wrestlerService.getAllWrestlersTitleID(titleId));
    }

    @PostMapping
    public ResponseEntity<WrestlerDTO> createWrestler(@RequestBody WrestlerDTO wrestlerDTO) {
        return ResponseEntity.ok(wrestlerService.createWrestler(wrestlerDTO));
    }
    @PutMapping("/{wrestlerId}")
    public ResponseEntity<WrestlerDTO> updateWrestler(@PathVariable Long wrestlerId, @RequestBody WrestlerDTO wrestlerDTO) {
        return ResponseEntity.ok(wrestlerService.updateWrestler(wrestlerId, wrestlerDTO));
    }

    @DeleteMapping("/{wrestlerId}")
    public ResponseEntity<String> deleteWrestler(@PathVariable Long wrestlerId) {
        wrestlerService.deleteWrestler(wrestlerId);
        return ResponseEntity.ok("Wrestler deleted");
    }
}
