package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.dto.MentorDto;
import com.stepintoprofession.registration_service.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentor")
public class MentorController {

    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<MentorDto> add(@RequestBody MentorDto dto) {
        return ResponseEntity.ok(mentorService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return mentorService.delete(id);
    }

    @PatchMapping
    public ResponseEntity<MentorDto> update(@RequestBody MentorDto body) {
        return ResponseEntity.ok(mentorService.save(body));
    }

    @GetMapping
    public List<MentorDto> findAll() {
        return mentorService.findALL();
    }

    @GetMapping("/internship")
    public List<MentorDto> findBy(String internship) {
        return mentorService.findByInternShip(internship);
    }
}