package com.example.Wrestling.controller;

import com.example.Wrestling.dto.PromotionDTO;
import com.example.Wrestling.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions/api/v1")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    @GetMapping
    public ResponseEntity<List<PromotionDTO>> getAllPromotions(@RequestParam(required = false) String search) {
        if(search == null) return ResponseEntity.ok(promotionService.getAllPromotions());
        return ResponseEntity.ok(promotionService.getPromotionByName(search));
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> getPromotion(@PathVariable long promotionId) {
        return ResponseEntity.ok(promotionService.getPromotionById(promotionId));
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        return ResponseEntity.ok(promotionService.createPromotion(promotionDTO));
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable long promotionId, @RequestBody PromotionDTO promotionDTO) {
        return ResponseEntity.ok(promotionService.updatePromotion(promotionId, promotionDTO));
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<String> deletePromotion(@PathVariable long promotionId) {
        promotionService.deletePromotion(promotionId);
        return ResponseEntity.ok("Promotion deleted");
    }

}
