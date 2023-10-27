package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.dto.RecruiterDto;
import com.stepintoprofession.registration_service.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    @PostMapping
    public ResponseEntity<RecruiterDto> add(@RequestBody RecruiterDto dto) {
        return ResponseEntity.ok(recruiterService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return recruiterService.delete(id);
    }

    @PatchMapping
    public ResponseEntity<RecruiterDto> update(@RequestBody RecruiterDto body) {
        return ResponseEntity.ok(recruiterService.save(body));
    }

    @GetMapping
    public List<RecruiterDto> findAll() {
        return recruiterService.findALL();
    }

    @GetMapping("/internship")
    public List<RecruiterDto> findBy(String internship) {
        return recruiterService.findByInternShip(internship);
    }
}
