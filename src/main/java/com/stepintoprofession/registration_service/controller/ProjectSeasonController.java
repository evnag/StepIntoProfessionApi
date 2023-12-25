package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.dto.ProjectSeasonDto;
import com.stepintoprofession.registration_service.service.ProjectSeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/season")
public class ProjectSeasonController {

    private final ProjectSeasonService seasonService;

    @PostMapping
    public ResponseEntity<ProjectSeasonDto> add(@RequestBody @Validated ProjectSeasonDto dto) {
        return ResponseEntity.ok(seasonService.save(dto));
    }

    @DeleteMapping("/{seasonNumber}")
    public ResponseEntity<Void> delete(@PathVariable Integer seasonNumber) {
        return seasonService.delete(seasonNumber);
    }

    @PatchMapping
    public ResponseEntity<ProjectSeasonDto> update(@RequestBody @Validated ProjectSeasonDto dto) {
        return ResponseEntity.ok(seasonService.updateSeason(dto));
    }

    @GetMapping
    public List<ProjectSeasonDto> findAll() {
        return seasonService.findAll();
    }
}
