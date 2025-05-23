package com.example.Wrestling.controller;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("titles/api/v1")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleDTO>> getAllTitles(@RequestParam(required = false) String search) {
        if(search == null)
        {
            List<TitleDTO> titles = titleService.getAllTitles();
            return ResponseEntity.ok(titles);
        }
        return ResponseEntity.ok(titleService.getAllBySearch(search));
    }
    @GetMapping("/{titleId}")
    public ResponseEntity<TitleDTO> getTitleById(@PathVariable int titleId) {
        return ResponseEntity.ok(titleService.getTitleById(titleId));
    }
    @GetMapping("/titles")
    public ResponseEntity<List<TitleDTO>> getTitlesByWrestler(@RequestParam(name = "wrestler") Long wrestlerId) {
        return ResponseEntity.ok(titleService.getAllByWrestler(wrestlerId));
    }
    @PostMapping
    public ResponseEntity<TitleDTO> createTitle(@RequestParam(name = "promo") Long promoId, @RequestBody TitleDTO titleDTO) {
        return ResponseEntity.ok(titleService.saveTitle(promoId, titleDTO));
    }
    @PutMapping("/{titleID}")
    public ResponseEntity<TitleDTO> updateTitle(@PathVariable int titleID, @RequestBody TitleDTO titleDTO) {
        return ResponseEntity.ok(titleService.updateTitle(titleID, titleDTO));
    }
    @DeleteMapping("/{titleID}")
    public ResponseEntity<String> deleteTitle(@PathVariable int titleID) {
        titleService.deleteTitle(titleID);
        return ResponseEntity.ok("Title deleted");
    }

}
