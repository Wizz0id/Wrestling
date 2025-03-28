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
    public ResponseEntity<List<TitleDTO>> getAllTitles() {
        return ResponseEntity.ok(titleService.getAllTitles());
    }
    @GetMapping("/{titleId}")
    public ResponseEntity<TitleDTO> getTitleById(@PathVariable int titleId) {
        return ResponseEntity.ok(titleService.getTitleById(titleId));
    }
    @PostMapping
    public ResponseEntity<TitleDTO> createTitle(@RequestBody TitleDTO titleDTO) {
        return ResponseEntity.ok(titleService.saveTitle(titleDTO));
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
