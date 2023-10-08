package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.Intern;
import com.stepintoprofession.registration_service.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interns")
public class InternController {

    private final InternService internService;

    @PostMapping
    public ResponseEntity<Intern> add(@RequestBody Intern intern){
        return ResponseEntity.ok(internService.save(intern));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return internService.delete(id);
    }

    @PatchMapping("")
    public ResponseEntity<Intern> update(@RequestBody Intern body) {
        return ResponseEntity.ok(internService.save(body));
    }

    @GetMapping
    public List<Intern> findAll() {
        return internService.findALL();
    }

    @GetMapping("/findBy")
    public Intern findBy(@RequestParam String firstName, @RequestParam String lastName) {
        return internService.findByFirstNamesAndLastName(firstName, lastName);
    }
}
