package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.dto.InternDto;
import com.stepintoprofession.registration_service.model.entity.InternEntity;
import com.stepintoprofession.registration_service.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/intern")
public class InternController {

    private final InternService internService;

    @PostMapping
    public ResponseEntity<InternDto> add(@RequestBody InternEntity intern) {
        return ResponseEntity.ok(internService.save(intern));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return internService.delete(id);
    }

    @PatchMapping
    public ResponseEntity<InternDto> update(@RequestBody InternEntity body) {
        return ResponseEntity.ok(internService.save(body));
    }

    @GetMapping
    public List<InternDto> findAll() {
        return internService.findALL();
    }

    @GetMapping("/internship")
    public List<InternDto> findBy(String internship) {
        return internService.findByInternShip(internship);
    }
}
